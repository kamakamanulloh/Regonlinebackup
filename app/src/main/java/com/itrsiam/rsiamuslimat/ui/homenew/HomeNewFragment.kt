package com.itrsiam.rsiamuslimat.ui.homenew


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.api.Utils
import com.itrsiam.rsiamuslimat.cek_antrian.CekAntrianActivity
import com.itrsiam.rsiamuslimat.info.*
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalFragment
import com.itrsiam.rsiamuslimat.list_tiket.*
import com.itrsiam.rsiamuslimat.pasien.NoAntrianView
import com.itrsiam.rsiamuslimat.pasien.TicketViewActivity
import com.itrsiam.rsiamuslimat.pasien.asuransi.AsuransiFragment
import com.itrsiam.rsiamuslimat.pasien.bpjs.BpjsFragment
import com.itrsiam.rsiamuslimat.pasien.bpjs.BpjsFragmentNew
import com.itrsiam.rsiamuslimat.pasien.umum.PasienUmumFragment
import com.itrsiam.rsiamuslimat.pasien_baru.PasienBaruActivity
import com.itrsiam.rsiamuslimat.pengingat_kontrol.PengingatKontrolFragment
import com.itrsiam.rsiamuslimat.petunjuk.PetunjukFragment
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_new.*
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.input_pasien_baru.view.*
import kotlinx.android.synthetic.main.tiket_fragment.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeNewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeNewFragment : Fragment(),NoAntrianView,TiketView,InfoView  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var progressDialog : ProgressDialog
    private lateinit var presenter: TiketPresenter
    private lateinit var infoPresenter: InfoPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_home_new, container, false)
        progressDialog= ProgressDialog(requireContext())
        presenter= TiketPresenter(this)
        infoPresenter= InfoPresenter(this)
        presenter.getKartu(Utils.user_id!!)
        infoPresenter.getInfoTerbaru()
        progressDialog.setMessage("Sedang Menyiapkan Mohon Tunggu Sebentar")
        progressDialog.show()
        return root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvHeader.text="Selamat Datang "+Utils.user_name


        btn_kontrol.onClick {
            val pengingatKontrolFragment = PengingatKontrolFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, pengingatKontrolFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
        btn_pxlama.onClick {
//            val builder = AlertDialog.Builder(requireContext())
//            builder.setMessage("Apakah anda sudah pernah berkunjung ke RSIA Muslimat Jombang dan sudah mempunyai Nomor Rekam Medik ? ")
//                .setPositiveButton("Sudah",
//                    DialogInterface.OnClickListener { dialog, id ->
//                        // FIRE ZE MISSILES!
//                        jenisLayanan()
//                        dialog.dismiss()
//
//
//                    })
//                .setNegativeButton("Belum",
//                    DialogInterface.OnClickListener { dialog, id ->
//                        // User cancelled the dialog
//                        dialog.dismiss()
//
//                        pasienbaru()
//
//                    })
//            // Create the AlertDialog object and return it
//            builder.create()
//            builder.show()
            jenisLayanan()

        }
        btn_cekantrian.onClick {
            startActivity(Intent(requireContext(), CekAntrianActivity::class.java))
        }
        btn_jadwal.onClick {
            val jadwalFragment = JadwalFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, jadwalFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        btn_petunjuk.onClick {
            var petunjukFragment = PetunjukFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, petunjukFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
        btn_webrs.onClick {

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://www.rsiamuslimat.com/")
            startActivity(intent)
        }
        text_lihat_semua.onClick {
            var infoFragment = InfoFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, infoFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

    }

    private fun pasienbaru() {
        val view = layoutInflater.inflate(R.layout.input_pasien_baru, null)
        val dialog = context?.let { it1 -> BottomSheetDialog(it1) }

        dialog?.setContentView(view)
        view.btn_lanjut.onClick {
            if (view.check_nik.isChecked){
                val intent=Intent(context, PasienBaruActivity::class.java)
                intent.putExtra("nik", view.et_nik.text.toString())

                startActivity(intent)
            }

            else{
                view.check_nik.error = "Harap Checklist Persetujuan Sebelum Melanjutkan"
            }
        }
        dialog?.show()

    }



    private fun jenisLayanan() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        val dialog = context?.let { it1 -> BottomSheetDialog(it1) }
        dialog?.setContentView(view)
        view.umum.setOnClickListener {
            var pickpowerfrag = PasienUmumFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, pickpowerfrag)
                ?.addToBackStack(null)
                ?.commit()
            dialog?.dismiss()

        }
        view.bpjs.setOnClickListener {
            var bpjs = BpjsFragmentNew()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, bpjs)
                ?.addToBackStack(null)
                ?.commit()
            dialog?.dismiss()

        }
        view.asuransi.setOnClickListener {

            var asuransi = AsuransiFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, asuransi)
                ?.addToBackStack(null)
                ?.commit()
            dialog?.dismiss()
        }



        dialog?.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeNewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeNewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    @SuppressLint("SetTextI18n")
    override fun onSucces(
        namaPx: String?,
        regBufferNoAntrian: String?,
        pasienNama: String?,
        poliNama: String?,
        loginCustPhoneNumber: String?,
        jenisNama: String?,
        regBufferWaktu: String?,
        perusahaanNama: String?,
        regBufferId: String?,
        regBufferTanggal: String?,
        usrName: String?,
        noRm: String?,
        regBufferNobpjs: String?
    ) {
//        btn_qrcode.isVisible=true
//
//        btn_qrcode.onClick {
//            val tiketintent= Intent(context, TicketViewActivity::class.java)
//            tiketintent.putExtra("buffer_id",regBufferId)
//            tiketintent.putExtra("no_antrian",regBufferNoAntrian)
//            tiketintent.putExtra("nm_poli",poliNama)
//            tiketintent.putExtra("nm_dokter",usrName)
//            tiketintent.putExtra("jam",regBufferWaktu)
//            tiketintent.putExtra("tanggal",regBufferTanggal)
//            tiketintent.putExtra("jenis_px",jenisNama)
//            tiketintent.putExtra("nm_px",namaPx)
//            tiketintent.putExtra("rm_px",noRm)
//            tiketintent.putExtra("nm_perusahaan",perusahaanNama)
//            startActivity(tiketintent)
//        }

    }


    override fun onFailedGet(msg: String?) {
        text.text="Belum Ada Kunjungan"
        textgeser.visibility=View.GONE
    }

    override fun onFailure(msg: String?) {

    }

    override fun onGetTiket(data: List<TiketResults?>?) {



        text.text="Kartu Registrasi Anda"


        rv_card.adapter= KartuRegAdapter(data as List<TiketResults>,object : KartuRegAdapter.onClickItem{
            override fun clicked(item: TiketResults?) {
                startActivity<TicketViewActivity>("dataItem" to item)


            }
        })
    }

    override fun onFailedGetTiket(msg: String) {

        text.text="Belum Ada Kunjungan"

        textgeser.visibility=View.GONE

    }

    override fun onGet(msg: String) {

    }

    override fun onFailed(msg: String) {
        text.text="Belum Ada Kunjungan"

        textgeser.visibility=View.GONE
    }

    override fun onSuccessInfo(data: List<ResultItemInfo?>?) {
        rv_info_new.adapter= InfoAdapter(data as List<ResultItemInfo>)
        progressDialog.dismiss()
    }

    override fun onFailedInfo(msg: String) {
        progressDialog.dismiss()
        alert {
            message=msg
        }.show()
    }

}