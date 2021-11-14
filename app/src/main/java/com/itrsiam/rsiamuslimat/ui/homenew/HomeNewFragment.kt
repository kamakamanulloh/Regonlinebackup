package com.itrsiam.rsiamuslimat.ui.homenew

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.api.Utils
import com.itrsiam.rsiamuslimat.cari_dokter.CariDokterFragment
import com.itrsiam.rsiamuslimat.cek_antrian.CekAntrianActivity
import com.itrsiam.rsiamuslimat.diagnosa.DiagnosaFragment
import com.itrsiam.rsiamuslimat.info.InfoFragment
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalFragment
import com.itrsiam.rsiamuslimat.ketentuan.KetentuanFragment
import com.itrsiam.rsiamuslimat.laboratorium.LabFragment

import com.itrsiam.rsiamuslimat.list_tiket.BatalRegFragment
import com.itrsiam.rsiamuslimat.list_tiket.TiketFragment
import com.itrsiam.rsiamuslimat.pasien.NoAntrianPresenter
import com.itrsiam.rsiamuslimat.pasien.NoAntrianView
import com.itrsiam.rsiamuslimat.pasien.TicketViewActivity
import com.itrsiam.rsiamuslimat.pasien.asuransi.AsuransiFragment
import com.itrsiam.rsiamuslimat.pasien.bpjs.BpjsFragment
import com.itrsiam.rsiamuslimat.pasien.umum.PasienUmumFragment
import com.itrsiam.rsiamuslimat.pasien_baru.PasienBaruActivity
import com.itrsiam.rsiamuslimat.pengingat_kontrol.PengingatKontrolFragment
import com.itrsiam.rsiamuslimat.petunjuk.PetunjukFragment
import com.itrsiam.rsiamuslimat.radiologi.RadilogiFragment
import com.itrsiam.rsiamuslimat.riwayat_periksa.RiwayatPeriksaFragment
import com.itrsiam.rsiamuslimat.saran.SaranPresenter
import com.itrsiam.rsiamuslimat.saran.SaranView
import com.itrsiam.rsiamuslimat.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*
import kotlinx.android.synthetic.main.fragment_home.*


import kotlinx.android.synthetic.main.fragment_home_new.*
import kotlinx.android.synthetic.main.fragment_home_new.btn_qrcode
import kotlinx.android.synthetic.main.fragment_home_new.view.*
import kotlinx.android.synthetic.main.item_saran.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeNewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeNewFragment : Fragment(),NoAntrianView  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


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

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvHeader.text="Selamat Datang "+Utils.user_name
        btn_pxbaru.onClick {
            val pasienBaruActivity= Intent(context, PasienBaruActivity::class.java)
            startActivity(pasienBaruActivity)
        }
        btn_pxlama.onClick {
           jenisLayanan()

        }
        btn_cekantrian.onClick {
            startActivity(Intent(requireContext(),CekAntrianActivity::class.java))
        }
        btn_radiologi.onClick {
            val radiologiFragment=RadilogiFragment()
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.nav_host_fragment,radiologiFragment)
                    ?.addToBackStack(null)
                    ?.commit()
        }
        btn_lab.onClick {
            val labFragment = LabFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, labFragment)
                ?.addToBackStack(null)
                ?.commit()

        }
        btn_jadwal.onClick {
            val jadwalFragment = JadwalFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, jadwalFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
        btn_diagnosa.setOnClickListener {
            val diagnosaFragment = DiagnosaFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, diagnosaFragment)
                ?.addToBackStack(null)
                ?.commit()

        }

        btn_kontrol.onClick {
            val pengingatKontrolFragment = PengingatKontrolFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, pengingatKontrolFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
        btn_periksa.onClick {
            val riwayatPeriksaFragment = RiwayatPeriksaFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, riwayatPeriksaFragment)
                ?.addToBackStack(null)
                ?.commit()
        }


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
            var bpjs = BpjsFragment()
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
        btn_qrcode.isVisible=true
        tvnoantrian.text=regBufferNoAntrian
        tv_kunjungan.text= " No Antrian Anda Tanggal $regBufferTanggal"
        tv_dokter.text="Poli $poliNama Dokter $usrName"
        btn_qrcode.onClick {
            val tiketintent= Intent(context, TicketViewActivity::class.java)
            tiketintent.putExtra("buffer_id",regBufferId)
            tiketintent.putExtra("no_antrian",regBufferNoAntrian)
            tiketintent.putExtra("nm_poli",poliNama)
            tiketintent.putExtra("nm_dokter",usrName)
            tiketintent.putExtra("jam",regBufferWaktu)
            tiketintent.putExtra("tanggal",regBufferTanggal)
            tiketintent.putExtra("jenis_px",jenisNama)
            tiketintent.putExtra("nm_px",namaPx)
            tiketintent.putExtra("rm_px",noRm)
            tiketintent.putExtra("nm_perusahaan",perusahaanNama)
            startActivity(tiketintent)
        }

    }

    override fun onFailed(msg: String?) {
        tvkartu.text="Belum Ada Kunjungan"
    }

    override fun onFailure(msg: String?) {

    }
}