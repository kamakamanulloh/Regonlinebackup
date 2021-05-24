package com.itrsiam.rsiamuslimat.pasien.umum

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.itrsiam.rsiamuslimat.DatePickerFragment
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.api.Utils
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalAdapter
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalPresenter
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalResultItem
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalView
import com.itrsiam.rsiamuslimat.jumlah.JumlahPresenter
import com.itrsiam.rsiamuslimat.jumlah.JumlahView
import com.itrsiam.rsiamuslimat.lupa_rm.LupaRm
import com.itrsiam.rsiamuslimat.pasien.CekRMView
import com.itrsiam.rsiamuslimat.pasien.PasienPresenter
import com.itrsiam.rsiamuslimat.pasien.SkringActivity
import com.itrsiam.rsiamuslimat.poli.ListPoliPresenter
import com.itrsiam.rsiamuslimat.poli.ListPoliView
import com.itrsiam.rsiamuslimat.poli.PoliAdapter
import com.itrsiam.rsiamuslimat.poli.PoliResultItem
import kotlinx.android.synthetic.main.input_pasien_umum.*
import kotlinx.android.synthetic.main.pasien_umum_fragment.*

import kotlinx.android.synthetic.main.pasien_umum_fragment.luparm
import kotlinx.android.synthetic.main.pasien_umum_fragment.toolbar

import kotlinx.android.synthetic.main.pasien_umum_fragment.txtrm
import kotlinx.android.synthetic.main.persetujuan.view.*

import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import retrofit2.Call
import retrofit2.Callback
import java.text.SimpleDateFormat
import java.util.*


class PasienUmumFragment : Fragment(),CekRMView,ListPoliView,JadwalView,JumlahView{

    companion object {
        fun newInstance() = PasienUmumFragment()
    }

    private lateinit var viewModel: PasienUmumViewModel


    private   lateinit var presenter: PasienPresenter
    private   lateinit var jumlahPresenter: JumlahPresenter
    private lateinit var jadwalPresenter: JadwalPresenter
    private lateinit var poliAdapter: PoliAdapter
    private lateinit var listpolipresenter: ListPoliPresenter
    var tanggal: String? =null
    var poli_id: String? =null
    var jadwal_id: String? =null

    var tanggal_lahir: String? =null
    var jumlah: Int? =null
    var pasienid: String? =null
    var dokterid: String? =null
    var nm_px: String? =null
    var jam: String? =null
    var nm_poli: String? =null
    var nm_dokter: String? =null
    var rm_px: String? =null
    var kuota: String? =null
    private lateinit var progressDialog : ProgressDialog
    lateinit var datePicker: DatePickerFragment
    var scannedResult: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pasien_umum_fragment, container, false)
        ButterKnife.bind(this, view)


        return view
    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listpolipresenter= ListPoliPresenter(this)
        progressDialog = ProgressDialog(requireContext())
        //for crate home button
        val activity = activity as AppCompatActivity?
        activity?.setSupportActionBar(toolbar)
        activity?.setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(R.color.white)
        datePicker = DatePickerFragment(requireContext(), true)
        presenter=PasienPresenter(this)
        jadwalPresenter= JadwalPresenter(this)
        jumlahPresenter= JumlahPresenter(this)
        btnscanrm.onClick {
            IntentIntegrator(getActivity()).initiateScan()
        }
        btncarirm.setOnClickListener(View.OnClickListener {
            val norm = txtrm.text.toString()
            if (norm.isEmpty() || tvtl.text.toString().isEmpty()){
                txtrm.error="Kolom Harus Diisi"
                tvtl.error="Kolom Harus Diisi"
            }
            else{
                progressDialog.setMessage("Application is loading, please wait")
                progressDialog.show()
                presenter.cekrm(norm,tanggal_lahir.toString())
            }



        })
        btntgllahir.setOnClickListener(View.OnClickListener {
            onDate()
        })
        luparm.onClick {
            var lupaRm = LupaRm()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, lupaRm)
                ?.addToBackStack(null)
                ?.commit()
        }
//        baseUrl = "http://103.53.78.78/api_regonline/"
//        fetchData();
       btntgl.setOnClickListener(View.OnClickListener {
           if (txtrmhasil.equals(" ")){
               alert {
                   message="Pastikan RM Anda Terdaftar"
               }

           }
           else{
               onDateperiksa()

           }

       })



        btnnext.onClick {
            val currentDate: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            when {
                tanggal.equals(currentDate) -> {
                    requireContext().alert {
                        message="Mohon Maaf Pendaftaran Maksimal h-1 Sebelum Tanggal Kunjungan Silahkan Pilih Tanggal Laiinya"
                    }.show()
                }
                jml.text.toString() == "Sisa Kuota : 0" -> {
                    requireContext().alert {
                        message="Mohon Maaf Kuota Sudah Penuh Silahkan Pilih Tanggal Laiinya"
                    }.show()
                }
                else -> {
                    perstujuan()


//                    requireContext().alert {
//                        message=tanggal+"-"+ currentDate
//                    }.show()

                }
            }



//            startActivity(Intent(context, SkringActivity::class.java))


        }


    }

    private fun perstujuan() {
        val view = layoutInflater.inflate(R.layout.persetujuan, null)
        val dialog = context?.let { it1 -> BottomSheetDialog(it1) }
        dialog?.setContentView(view)
        view.btn_setuju.onClick {
            if (view.checkBox1.isChecked){
                screeningActivty()
            }
            else{
                view.tvnotify.text="Harap Checklist Persetujuan Sebelum Melanjutkan"
            }
        }
    }

    private fun screeningActivty() {
        val intent=Intent(context, SkringActivity::class.java)
        intent.putExtra("pasien_id",pasienid)
        intent.putExtra("poli_id",poli_id)
        intent.putExtra("dokter_id",dokterid)
        intent.putExtra("tanggal",tanggal)
        intent.putExtra("no_hp",Utils.nohp)
        intent.putExtra("user_id",Utils.user_id)
        intent.putExtra("jenis_px","2")
        intent.putExtra("nm_poli",nm_poli)
        intent.putExtra("nm_dokter",nm_dokter)
        intent.putExtra("jam",jam)
        intent.putExtra("no_bpjs"," ")
        intent.putExtra("no_rujukan","")
        intent.putExtra("no_asuransi"," ")
        intent.putExtra("asuransi_id"," ")
        intent.putExtra("nm_px",nm_px)
        intent.putExtra("rm_px",rm_px)
        intent.putExtra("id_jadwal",jadwal_id)

        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        var result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if(result != null){

            if(result.contents != null){

                presenter.cekrm(result.contents," ")


            } else {
               alert {
                   message="Scan Gagal"
               }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {

        outState.putString("scannedResult", scannedResult)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        savedInstanceState?.let {
            presenter.cekrm(scannedResult," ")

        }
    }

    private fun onDateperiksa() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)
        val minDate = Calendar.getInstance()
        minDate.add(Calendar.DAY_OF_MONTH,1)
        datePicker.setMinDate(minDate.timeInMillis)
        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.DAY_OF_MONTH, 3)
        datePicker.setMaxDate(maxDate.timeInMillis)
        datePicker.showDialog(d, m, y, object : DatePickerFragment.Callback {
            @SuppressLint("SetTextI18n")
            override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                val mon = month + 1
                val monthStr = if (mon < 10) "0${mon}" else "${mon}"
                tvtgl.text = ("$dayStr-$mon-$year")
                tanggal = "$dayStr-$monthStr-$year"
                listpolipresenter.getPoli()
            }
        })
    }


    private fun onDate() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)
        datePicker.showDialog(d, m, y, object : DatePickerFragment.Callback {
            @SuppressLint("SetTextI18n")
            override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                val mon = month + 1
                val monthStr = if (mon < 10) "0${mon}" else "${mon}"
                tvtl.text = ("$dayStr-$mon-$year")
                tanggal_lahir = "$year-$monthStr-$dayStr"
            }
        })
    }

//    private fun onDateperiksa() {
//        val c = Calendar.getInstance()
//        val year = c.get(Calendar.YEAR)
//        val month = c.get(Calendar.MONTH)
//        val day = c.get(Calendar.DAY_OF_MONTH)
//
//        mPickTimeBtn.setOnClickListener {
//
//            val dpd = DatePickerDialog(this, OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//                // Display Selected date in TextView
//                textView.setText("" + dayOfMonth + " " + month + ", " + year)
//            }, year, month, day)
//            dpd.show()
//
//        }
//
//    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            viewModel = ViewModelProviders.of(this).get(PasienUmumViewModel::class.java)
            // TODO: Use the ViewModel
        }

        override fun onSuccessLogin(
            msg: String?,
            pasien_id: String?,
            pasien_rm: String?,
            pasien_nama: String?,
            pasien_tl: String?,
            pasien_alamat: String?,
            cust_usr_no_identitas:String?,
            cust_usr_no_jaminan: String?
        ) {
            progressDialog.dismiss()
            inputumum.isVisible=true
            txtrmhasil.setText(pasien_rm)
            txtnama.setText(pasien_nama)
            txttgllahir.setText(pasien_tl)
            txtalamat.setText(pasien_alamat)
            txthp.setText(com.itrsiam.rsiamuslimat.api.Utils.nohp)
            pasienid=pasien_id
            nm_px=pasien_nama
            rm_px=pasien_rm


        }

        override fun onFailedLogin(msg: String?) {
            progressDialog.dismiss()
            context?.alert{
                title = "Peringatan"
                message="No RM Tidak Valid Atau Belum Terdaftar Silahkan Cek Kembali"
            }?.show()
//            val dialogBuilder = AlertDialog.Builder(context)
//
//            // set message of alert dialog
//            dialogBuilder.setMessage(msg)
//                // if the dialog is cancelable
//                .setCancelable(false)
//                // positive button text and action
//
//            // create dialog box
//            val alert = dialogBuilder.create()
//            // set title for alert dialog box
//            alert.setTitle("AlertDialogExample")
//            // show alert dialog
//            alert.show()
//            alert.dismiss()

        }



        override fun onFailure(msg: String?) {
//            val dialogBuilder = AlertDialog.Builder(context)
//
//            // set message of alert dialog
//            dialogBuilder.setMessage("Failure")
//                // if the dialog is cancelable
//                .setCancelable(false)
//                // positive button text and action
//
//            // create dialog box
//            val alert = dialogBuilder.create()
//            // set title for alert dialog box
//            alert.setTitle("AlertDialogExample")
//            // show alert dialog
//            alert.show()
//            alert.dismiss()

        }

    override fun onSuccessGet(data: List<PoliResultItem?>?) {
        listpoli.adapter=PoliAdapter(requireContext(), data as List<PoliResultItem>)
        poliAdapter=PoliAdapter(requireContext(), data)

        listpoli.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

                listdokter.adapter=null

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               poli_id =data[position].poliId.toString()
                nm_poli=data[position].poliNama.toString()
                if (nm_poli.equals("Poli Umum")){
                    listdokter.adapter=null

                }
                else{
                    jadwalPresenter.getJadwal(poli_id!!, tanggal.toString())
                }




            }
        }

    }

    override fun onFailedGet(msg: String) {

    }

    override fun onGetJadwal(data: List<JadwalResultItem?>?) {
        listdokter.adapter=JadwalAdapter(requireContext(), data as List<JadwalResultItem>)

        listdokter.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                jadwal_id =data[position].jadwalDokterId.toString()
                dokterid=data[position].id_dokter.toString()
                nm_dokter=data[position].dokterNama.toString()
                jam=data[position].jadwalDokterJamMulai.toString()
                jumlahPresenter.getJumlah(jadwal_id)
                kuota=data[position].kuota.toString()




            }
        }
    }

    override fun onFailedGetJadwal(msg: String) {

    }

    @SuppressLint("SetTextI18n")
    override fun onSuccessJumlah(msg: String) {
        jumlah=msg.toInt()
        val kuotajadwal=kuota?.toInt()
        val sisa= kuotajadwal?.minus(jumlah!!)
        jml.text = "Sisa Kuota : $sisa"
        tv_kuota.text="Kuota : $kuotajadwal"


    }

    @SuppressLint("SetTextI18n")
    override fun onFailedJumlah(msg: String) {


    }


}

private fun <T> Call<T>.enqueue(callback: Callback<List<PoliResultItem>>) {

}







