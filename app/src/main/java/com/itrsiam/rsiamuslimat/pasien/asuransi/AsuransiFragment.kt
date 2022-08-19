package com.itrsiam.rsiamuslimat.pasien.asuransi


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
import com.itrsiam.rsiamuslimat.kartu.EkartuResultItem
import com.itrsiam.rsiamuslimat.kartu.KartuFragment
import com.itrsiam.rsiamuslimat.kartu.KartuPresenter
import com.itrsiam.rsiamuslimat.kartu.KartuView
import com.itrsiam.rsiamuslimat.pasien.CekRMView
import com.itrsiam.rsiamuslimat.pasien.EkartuPasienAdapter
import com.itrsiam.rsiamuslimat.pasien.PasienPresenter
import com.itrsiam.rsiamuslimat.pasien.SkringActivity
import com.itrsiam.rsiamuslimat.poli.ListPoliPresenter
import com.itrsiam.rsiamuslimat.poli.ListPoliView
import com.itrsiam.rsiamuslimat.poli.PoliAdapter
import com.itrsiam.rsiamuslimat.poli.PoliResultItem
import kotlinx.android.synthetic.main.asuransi_fragment.*
import kotlinx.android.synthetic.main.input_pasien_asuransi.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class AsuransiFragment : Fragment(), CekRMView , ListPoliView, JadwalView,AsuransiView ,
    JumlahView,KartuView {

    companion object {
        fun newInstance() = AsuransiFragment()
    }
    private lateinit var kartuPresenter: KartuPresenter
    private   lateinit var jumlahPresenter: JumlahPresenter
    private lateinit var viewModel: AsuransiViewModel
    private lateinit var poliAdapter: PoliAdapter
    private lateinit var asuransiPresenter: AsuransiPresenter
    private   lateinit var presenter: PasienPresenter
    private lateinit var jadwalPresenter: JadwalPresenter
    private lateinit var listpolipresenter: ListPoliPresenter
    val c = Calendar.getInstance()
    var jumlah: Int? =null
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
    var asuransiId: String? =null
    var asuransiNama: String? =null
    var kuota: String? =null
    private lateinit var progressDialog : ProgressDialog
    lateinit var datePicker: DatePickerFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.asuransi_fragment, container, false)
        (activity as AppCompatActivity).supportActionBar
        (activity as AppCompatActivity).supportActionBar?.title = "Pendaftaran Pasien Asuransi"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AsuransiViewModel::class.java)
        // TODO: Use the ViewModel
        jumlahPresenter= JumlahPresenter(this)
        presenter=PasienPresenter(this)
        asuransiPresenter=AsuransiPresenter(this)
        asuransiPresenter.getAsuransi()
        listpolipresenter= ListPoliPresenter(this)
        progressDialog= ProgressDialog(requireContext())
        kartuPresenter= KartuPresenter(this)
        kartuPresenter.ekartu(Utils.user_id.toString())
        jadwalPresenter= JadwalPresenter(this)
        datePicker = DatePickerFragment(requireContext(), true)

        progressDialog.setMessage("Proses Ambil Data Mohon Tunggu")
        progressDialog.show()
        btntgl.setOnClickListener(View.OnClickListener {
//           if (txtrmhasil.equals(" ")){
//               alert {
//                   message="Pastikan RM Anda Terdaftar"
//               }
//
//           }
//            else{
//               onDateperiksa()
//           }
            onDateperiksa()
        })




        tv_pasien.onClick {
            val kartuFragment = KartuFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, kartuFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
        btnnext.onClick {
            val currentDate: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            when {
                tanggal.equals(currentDate) -> {
                    requireContext().alert {
                        message="Mohon Maaf Pendaftaran Maksimal h-1 Sebelum Tanggal Kunjungan Silahkan Pilih Tanggal Laiinya"
                    }.show()
                }
                jmlasuransi.text.toString() == "Sisa Kuota : 0" -> {
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
                    intent.putExtra("jenis_px","7")
                    intent.putExtra("nm_poli",nm_poli)
                    intent.putExtra("nm_dokter",nm_dokter)
                    intent.putExtra("jam",jam)
                    intent.putExtra("no_bpjs","")
                    intent.putExtra("no_rujukan","")
                    intent.putExtra("no_asuransi",noasuransi.text.toString())
                    intent.putExtra("asuransi_id",asuransiId)
                    intent.putExtra("nm_px",nm_px)
                    intent.putExtra("rm_px",rm_px)
                    intent.putExtra("id_jadwal",jadwal_id)
                    intent.putExtra("asuransi_nama",asuransiNama)



                    startActivity(intent)
                }

                }
        }


    }
    private fun onDate() {
//        val cal = Calendar.getInstance()
//        val d = cal.get(Calendar.DAY_OF_MONTH)
//        val m = cal.get(Calendar.MONTH)
//        val y = cal.get(Calendar.YEAR)
//        datePicker.showDialog(d, m, y, object : DatePickerFragment.Callback {
//            @SuppressLint("SetTextI18n")
//            override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
//                val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
//                val mon = month + 1
//
//                val monthStr = if (mon < 10) "0${mon}" else "${mon}"
//                tvtlasu.text = ("$dayStr-$mon-$year")
//                tanggal =  "$year-$monthStr-$dayStr"
//            }
//        })
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
                tvtglasu.text = ("$dayStr-$mon-$year")
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
        inputasuransi.isVisible=true

        txtnama.setText(pasien_nama)
        txttgllahir.setText(pasien_tl)
        txtalamat.setText(pasien_alamat)
        txthp.setText(com.itrsiam.rsiamuslimat.api.Utils.nohp)
        noasuransi.setText(cust_usr_no_jaminan)
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
        listpoliasu.adapter= PoliAdapter(requireContext(), data as List<PoliResultItem>)
        poliAdapter=PoliAdapter(requireContext(), data)

        listpoliasu.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                listdokterasura.adapter=null
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                poli_id =data[position].poliId.toString()
                nm_poli=data[position].poliNama.toString()
                progressDialog.setMessage("Application is loading, please wait")
                progressDialog.show()
                jadwalPresenter.getJadwal(poli_id!!, tanggal.toString())





            }
        }
    }

    override fun onFailedGet(msg: String) {

    }

    override fun onGetJadwal(data: List<JadwalResultItem?>?) {
        listdokterasura.adapter= JadwalAdapter(requireContext(), data as List<JadwalResultItem>)
        progressDialog.dismiss()
        listdokterasura.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
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
                kuota=data[position].kuota.toString()
                jumlahPresenter.getJumlah(jadwal_id)



            }
        }
    }

    override fun onFailedGetJadwal(msg: String) {

    }

    override fun onSuccessGetAsuransi(data: List<AuransiResultItem?>?) {
        jns_asuransi.adapter= AsuransiAdapter(requireContext(), data as List<AuransiResultItem>)

        jns_asuransi.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                progressDialog.dismiss()
                asuransiId =data[position].perusahaanId.toString()
                asuransiNama=data[position].perusahaanNama.toString()





            }
        }
    }

    override fun onFailedGetAsuransi(msg: String) {

    }

    @SuppressLint("SetTextI18n")
    override fun onSuccessJumlah(msg: String) {
        jumlah=msg.toInt()
        val kuotajadwal=kuota?.toInt()
        val sisa= kuotajadwal?.minus(jumlah!!)
        jmlasuransi.text= "Sisa Kuota : $sisa"
        kuotaasuransi.text= "Kuota : $kuotajadwal"

    }

    @SuppressLint("SetTextI18n")
    override fun onFailedJumlah(msg: String) {


    }

    override fun onSuccessAdd(msg: String?) {

    }

    override fun onSuccessDel(msg: String?) {

    }

    override fun onFailedAdd(msg: String?) {

    }

    override fun onSuccessGetList(data: List<EkartuResultItem?>?) {
        progressDialog.dismiss()
      rv_card_pasien.adapter= EkartuPasienAdapter(data as List<EkartuResultItem>,
          object : EkartuPasienAdapter.onClickItem{
              override fun clicked(item: EkartuResultItem?) {

                  tv_no_rm.text=item?.custUsrKode
                  tvtl.text=item?.custUsrTglLahir
                  inputasuransi.isVisible=true
                  pasienid = item?.custUsrId
                  nm_px = item?.custUsrNama
                  rm_px = item?.custUsrKode

                  txthp.setText(Utils.nohp)
                  txtnama.setText(item?.custUsrNama)
                  txtalamat.setText(item?.custUsrAlamat)

              }
          })
    }

    override fun onFailureAdd(msg: String?) {

    }


}
