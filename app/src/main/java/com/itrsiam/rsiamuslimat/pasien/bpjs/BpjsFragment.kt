@file:Suppress("DEPRECATION")

package com.itrsiam.rsiamuslimat.pasien.bpjs


import android.annotation.SuppressLint
import android.app.AlertDialog
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
import kotlinx.android.synthetic.main.bpjs_fragment.*
import kotlinx.android.synthetic.main.bpjs_fragment.inputbpjs
import kotlinx.android.synthetic.main.bpjs_fragment.inputlanjutbpjs
import kotlinx.android.synthetic.main.bpjs_fragment_old.*
import kotlinx.android.synthetic.main.input_pasien_bpjs.*
import kotlinx.android.synthetic.main.lanjutanformbpjs.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import java.text.SimpleDateFormat
import java.util.*

@Suppress("UNCHECKED_CAST")
class BpjsFragment : Fragment(), CekRMView
    ,ListPoliView, JadwalView, JumlahView ,TipeJKNView,CekKepesertaanView{

    companion object {
        fun newInstance() = BpjsFragment()
    }
    private   lateinit var jumlahPresenter: JumlahPresenter
    private   lateinit var Jknpresenter: JknPresenter
    private lateinit var viewModel: BpjsViewModel
    private lateinit var poliAdapter: PoliAdapter
    private   lateinit var presenter: PasienPresenter
    private lateinit var jadwalPresenter: JadwalPresenter
    private lateinit var listpolipresenter: ListPoliPresenter
    lateinit var cekKepesertaanPresenter: CekKepesertaanPresenter
    val c = Calendar.getInstance()
    lateinit var datePicker: DatePickerFragment
    var poli_id: String? =null
    var jadwal_id: String? =null
    var tanggal: String? =null
    var pasienid: String? =null
    var dokterid: String? =null
    var nm_px: String? =null
    var jam: String? =null
    var nm_poli: String? =null
    var nm_dokter: String? =null
    var rm_px: String? =null
    var jumlah: Int? =null
    var kuota: String? =null
    private lateinit var progressDialog : ProgressDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ;         val view= inflater.inflate(R.layout.bpjs_fragment_old, container, false)
        (activity as AppCompatActivity).supportActionBar
        (activity as AppCompatActivity).supportActionBar?.title = "Pendaftaran Pasien BPJS"
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val savedInstanceState1 = savedInstanceState
        super.onActivityCreated(savedInstanceState1)
        viewModel = ViewModelProviders.of(this).get(BpjsViewModel::class.java)
        // TODO: Use the ViewModel
        jumlahPresenter= JumlahPresenter(this)
        presenter=PasienPresenter(this)
        listpolipresenter= ListPoliPresenter(this)
        Jknpresenter= JknPresenter(this)
        Jknpresenter.getjkn()
        datePicker = DatePickerFragment(requireContext(), true)
        jadwalPresenter= JadwalPresenter(this)
        cekKepesertaanPresenter= CekKepesertaanPresenter(this)

        progressDialog = ProgressDialog(requireContext())
        btncarirmbpjs.setOnClickListener(View.OnClickListener {
            val norm = txtrm.text.toString()
            if (norm.isEmpty() || tvtlbpjs.text.toString().isEmpty()){
                txtrm.error="Kolom Harus Diisi"
                tvtlbpjs.error="Kolom Harus Diisi"
            }
            else{

                progressDialog.setMessage("Application is loading, please wait")
                progressDialog.show()


                presenter.cekrm(norm,tanggal.toString())
            }



        })
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = sdf.format(Date())
        btn_kepesertaan.onClick {
            progressDialog.setMessage("Application is loading, please wait")
            progressDialog.show()
            cekKepesertaanPresenter.cekPeserta(nobpjs.text.toString(),currentDate.toString())
        }
        btntgllahirbpjs.setOnClickListener(View.OnClickListener {
            onDate()
        })
        luparm.onClick {
            var lupaRm = LupaRm()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, lupaRm)
                ?.addToBackStack(null)
                ?.commit()
        }
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
                    val intent=Intent(context, SkringActivity::class.java)
                    intent.putExtra("pasien_id",pasienid)
                    intent.putExtra("poli_id",poli_id)
                    intent.putExtra("dokter_id",dokterid)
                    intent.putExtra("tanggal",tanggal)
                    intent.putExtra("no_hp",Utils.nohp)
                    intent.putExtra("user_id",Utils.user_id)
                    intent.putExtra("jenis_px","5")
                    intent.putExtra("nm_poli",nm_poli)
                    intent.putExtra("nm_dokter",nm_dokter)
                    intent.putExtra("jam",jam)
                    intent.putExtra("no_bpjs",nobpjs.text.toString())
                    intent.putExtra("no_rujukan",norujukan.text.toString())
                    intent.putExtra("no_asuransi"," ")
                    intent.putExtra("asuransi_id"," ")
                    intent.putExtra("nm_px",nm_px)
                    intent.putExtra("rm_px",rm_px)
                    intent.putExtra("id_jadwal",jadwal_id)


                    startActivity(intent)
                }

            }
        }



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
                tvtlbpjs.text = ("$dayStr-$mon-$year")
                tanggal =  "$year-$monthStr-$dayStr"
            }
        })
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
        inputbpjs.isVisible=true
        txtrmhasil.setText(pasien_rm)
        txtnama.setText(pasien_nama)
        txttgllahir.setText(pasien_tl)
        txtalamat.setText(pasien_alamat)
        txthp.setText(com.itrsiam.rsiamuslimat.api.Utils.nohp)
        nobpjs.setText(cust_usr_no_jaminan)
        noktp.setText(cust_usr_no_identitas)
        pasienid = pasien_id
        nm_px = pasien_nama
        rm_px = pasien_rm


    }


    override fun onFailedLogin(msg: String?) {
        progressDialog.dismiss()
        context?.alert{
            title = "Peringatan"
            message="No RM atau Tanggal Lahir Tidak Terdaftar"
        }?.show()
//        val dialogBuilder = AlertDialog.Builder(context)
//
//        // set message of alert dialog
//        dialogBuilder.setMessage(msg)
//            // if the dialog is cancelable
//            .setCancelable(false)
//            // positive button text and action
//
//            // negative button text and action
//            .setNegativeButton("Tutup", DialogInterface.OnClickListener {
//                    dialog, id -> dialog.cancel()
//            })
//
//        // create dialog box
//        val alert = dialogBuilder.create()
//        // set title for alert dialog box
//        alert.setTitle("Informasi")
//        // show alert dialog
//        alert.show()
    }

    override fun onFailure(msg: String?) {
        val dialogBuilder = AlertDialog.Builder(context)

        // set message of alert dialog
        dialogBuilder.setMessage("Failure")
            // if the dialog is cancelable
            .setCancelable(false)
        // positive button text and action

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("AlertDialogExample")
        // show alert dialog
        alert.show()

    }
    override fun onSuccessGet(data: List<PoliResultItem?>?) {
        listpolibpjs.adapter= PoliAdapter(requireContext(), data as List<PoliResultItem>)
        poliAdapter=PoliAdapter(requireContext(), data)

        listpolibpjs.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
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
        val dialogBuilder = AlertDialog.Builder(context)
//
//        // set message of alert dialog
        dialogBuilder.setMessage("Failure")
//            // if the dialog is cancelable
            .setCancelable(false)
//        // positive button text and action
//
//        // create dialog box
        val alert = dialogBuilder.create()
//        // set title for alert dialog box
//
//        // show alert dialog
        alert.show()
        alert.dismiss()
    }

    override fun onGetJadwal(data: List<JadwalResultItem?>?) {
        listdokter.adapter= JadwalAdapter(requireContext(), data as List<JadwalResultItem>)

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
        jml.text= "Sisa Kuota : $sisa"
        tv_kuota.text= "Kuota : $kuota"

    }

    @SuppressLint("SetTextI18n")
    override fun onFailedJumlah(msg: String) {

    }

    override fun onSuccessGetjkn(data: List<jknResultItem?>?) {
        tipe_jkn.adapter=JKNAdapter(requireContext(), data as List<jknResultItem>)

    }

    override fun onFailedGetjkn(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessCek(
        msg: String?,
        nama: String?,
        status: String?,
        asal: String?,
        jenis: String?
    ) {

        progressDialog.dismiss()

        txtnamapeserta.setText(nama)
        txtstatuspeserta.setText(status)
        txtfaskespeserta.setText(asal)
        txtjenispeserta.setText(jenis)
        inputlanjutbpjs.isVisible=true

    }

    override fun onFailedCek(msg: String?) {
        progressDialog.dismiss()
        alert {
            message=msg.toString()
        }.show()

    }


}
