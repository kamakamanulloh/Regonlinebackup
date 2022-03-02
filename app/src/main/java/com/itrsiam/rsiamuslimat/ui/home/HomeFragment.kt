package com.itrsiam.rsiamuslimat.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context.MODE_PRIVATE
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.google.android.material.bottomsheet.BottomSheetDialog

import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.api.Utils
import com.itrsiam.rsiamuslimat.cari_dokter.CariDokterFragment
import com.itrsiam.rsiamuslimat.cek_antrian.CekAntrianActivity
import com.itrsiam.rsiamuslimat.info.InfoFragment
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalFragment
import com.itrsiam.rsiamuslimat.ketentuan.KetentuanFragment
import com.itrsiam.rsiamuslimat.list_tiket.BatalRegFragment
import com.itrsiam.rsiamuslimat.list_tiket.TiketFragment
import com.itrsiam.rsiamuslimat.pasien.NoAntrianPresenter
import com.itrsiam.rsiamuslimat.pasien.NoAntrianView
import com.itrsiam.rsiamuslimat.pasien.TicketViewActivity
import com.itrsiam.rsiamuslimat.pasien.asuransi.AsuransiFragment
import com.itrsiam.rsiamuslimat.pasien.bpjs.BpjsFragment
import com.itrsiam.rsiamuslimat.pasien.umum.PasienUmumFragment
import com.itrsiam.rsiamuslimat.petunjuk.PetunjukFragment
import com.itrsiam.rsiamuslimat.saran.SaranPresenter
import com.itrsiam.rsiamuslimat.saran.SaranView
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_saran.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() ,SaranView,NoAntrianView{

    private lateinit var homeViewModel: HomeViewModel
    var dialog: AlertDialog.Builder? = null
    var inflater: LayoutInflater? = null
    var dialogView: View? = null
    var currentDate: String? =null
    private var calendar: Calendar? = null
    private lateinit var noAntrianPresenter: NoAntrianPresenter

    private lateinit var saranPresenter: SaranPresenter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)





        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }



    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.hide()


        informasi()
        saranPresenter= SaranPresenter(this)

        calendar = Calendar.getInstance();
        val sdf = SimpleDateFormat("yyyy/MM/dd")
         currentDate = sdf.format(Date())
        noAntrianPresenter= NoAntrianPresenter(this)
        noAntrianPresenter.getNoAntrian(Utils.user_id,currentDate)



//
//        val username = intent.getString("id")
//        tvgreeting.setText("Selamat Datang "+Utils.user_name)

        btnpetunjuk.onClick {
            var petunjukFragment =PetunjukFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, petunjukFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        btnketentuan.onClick {
            var ketentuanFragment =KetentuanFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, ketentuanFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

//        btn_saran.onClick {
//            Saran()
//        }

        et_cari.onClick {
            var cariDokterFragment = CariDokterFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, cariDokterFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        btn_antrian.onClick {
            startActivity(Intent(requireContext(),CekAntrianActivity::class.java))
        }


        infolayanan.setOnClickListener(View.OnClickListener {
            var jadwalFragment = JadwalFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, jadwalFragment)
                ?.addToBackStack(null)
                ?.commit()
        })

        webrs.onClick {
           val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://rsiamuslimat.co.id/")
            startActivity(intent)
        }
        
        btn_batal.onClick {
            var batalRegFragment = BatalRegFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, batalRegFragment)
                ?.addToBackStack(null)
                ?.commit()

        }


        bukti_reg.setOnClickListener(View.OnClickListener {
            var tiketFragment = TiketFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, tiketFragment)
                ?.addToBackStack(null)
                ?.commit()
        })

        btnumum.setOnClickListener(View.OnClickListener {
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


        })
    }


    fun checkFirstRun() {
        val isFirstRun: Boolean =
            requireContext().getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean(
                "isFirstRun",
                true
            )
        if (isFirstRun) {
            informasi()
            requireContext().getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putBoolean("isFirstRun", false)
                .apply()
        }
    }


    private fun informasi() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Informasi")
        builder.setMessage("Sebelum melakukan registrasi pastikan anda sudah mengetahui informasi terbaru dari kami")
            .setPositiveButton("Ya",
                DialogInterface.OnClickListener { dialog, id ->
                    // FIRE ZE MISSILES!
                    var infoFragment = InfoFragment()
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.nav_host_fragment, infoFragment)
                        ?.addToBackStack(null)
                        ?.commit()

                })
            .setNegativeButton("Tidak",
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                    dialog.dismiss()
                })
        builder.show()
        dialog?.show()


    }




    private fun Saran() {
        dialog = AlertDialog.Builder(requireContext())
        inflater = layoutInflater
        val  dialogView = inflater!!.inflate(R.layout.item_saran, null)
        dialog!!.setView(dialogView)
        dialog!!.setCancelable(true)

        dialog!!.setTitle("Form Customer Service ")


        dialog!!.setPositiveButton(
            "SUBMIT"
        ) { dialog, _ ->


            saranPresenter.insSaran(Utils.user_id, dialogView.et_pesan.text.toString())
        }
        dialog!!.setNegativeButton(
            "CANCEL"
        ) { dialog, which -> dialog.dismiss() }
        dialog!!.show()
    }

    override fun onSuccessIns(msg: String?) {
        alert {

            message=msg.toString()
        }.show()
    }

    override fun onFailedIns(msg: String?) {
        alert {

            message=msg.toString()
        }.show()
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
        tvNoAntrian.text=regBufferNoAntrian
        tvTitleNo.text= " No Antrian Anda Tanggal $regBufferTanggal"
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

    override fun onFailedGet(msg: String?) {
       tvTitleNo.text="Belum Ada Kunjungan"
    }

    override fun onFailure(msg: String?) {

    }


}


