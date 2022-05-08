
package com.itrsiam.rsiamuslimat.cek_antrian

import android.annotation.SuppressLint
import android.os.Build.ID
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.itrsiam.rsiamuslimat.DatePickerFragment
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalAdapter
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalPresenter
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalResultItem
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalView
import com.itrsiam.rsiamuslimat.poli.ListPoliPresenter
import com.itrsiam.rsiamuslimat.poli.ListPoliView
import com.itrsiam.rsiamuslimat.poli.PoliAdapter
import com.itrsiam.rsiamuslimat.poli.PoliResultItem
import kotlinx.android.synthetic.main.activity_cek_antrian.*
import kotlinx.android.synthetic.main.pasien_umum_fragment.*
import org.jetbrains.anko.alert
import java.text.SimpleDateFormat
import java.util.*

class CekAntrianActivity : AppCompatActivity(), ListPoliView, JadwalView,CekAntrianView {
    private lateinit var jadwalPresenter: JadwalPresenter
    private lateinit var poliAdapter: PoliAdapter
    private lateinit var listpolipresenter: ListPoliPresenter
    private lateinit var cekAntrianPresenter: cekAntrianPresenter
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
    lateinit var datePicker: DatePickerFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cek_antrian)
        listpolipresenter= ListPoliPresenter(this)
        jadwalPresenter= JadwalPresenter(this)
        cekAntrianPresenter=cekAntrianPresenter(this)

        listpolipresenter.getPoli()



    }


    override fun onSuccessGet(data: List<PoliResultItem?>?) {
        listPoli.adapter=PoliAdapter(this, data as List<PoliResultItem>)
        poliAdapter=PoliAdapter(this, data)


        listPoli.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

                listDokter.adapter=null

            }

            @SuppressLint("SimpleDateFormat")
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                poli_id =data[position].poliId.toString()
                nm_poli=data[position].poliNama.toString()
                if (nm_poli.equals("Poli Umum")){
                    listDokter.adapter=null

                }
                else{
                    val sdf = SimpleDateFormat("dd/M/yyyy")
                    val currentDate = sdf.format(Date())

                    jadwalPresenter.getJadwal(poli_id!!, " ")
                }




            }
        }

    }

    override fun onFailedGet(msg: String) {

    }

    override fun onGetJadwal(data: List<JadwalResultItem?>?) {
        tvDokter.isVisible=true
        listDokter.isVisible=true
        listDokter.adapter= JadwalAdapter(this, data as List<JadwalResultItem>)

        listDokter.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
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
                progress_bar_antrian.visibility=(View.VISIBLE)
                cekAntrianPresenter.getAntrian(poli_id.toString(), dokterid.toString())






            }
        }

    }

    override fun onFailedGetJadwal(msg: String) {

    }

    @SuppressLint("SetTextI18n")
    override fun onSuccessAntrian(
        reg_no_antrian: String?,
        poli_nama: String?,
        dokter_nama: String?
    ) {
        tvNomor.isVisible=true
        tv_nomor.isVisible=true
        tvPoli.isVisible=true
        tv_nomor.text=reg_no_antrian
        tvPoli.text= "$poli_nama ( $dokter_nama )"

        progress_bar_antrian.visibility=(View.GONE)

    }

    override fun onFailedAntri(msg: String) {

        tvNomor.isVisible=false
        tv_nomor.isVisible=false
        tvPoli.isVisible=false

    }


}