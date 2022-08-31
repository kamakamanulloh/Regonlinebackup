package com.itrsiam.rsiamuslimat.pasien

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.list_tiket.TiketPresenter
import com.itrsiam.rsiamuslimat.list_tiket.TiketResults
import com.itrsiam.rsiamuslimat.list_tiket.TiketView
import kotlinx.android.synthetic.main.activity_ticket_view.*
import org.jetbrains.anko.alert

class TicketViewActivity : AppCompatActivity(),TiketView {
    var multiFormatWriter: MultiFormatWriter = MultiFormatWriter()
    private lateinit var presenter: TiketPresenter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_view)
        var jenis_px:String?=null
        presenter= TiketPresenter(this)
        val tiketResults=intent.getSerializableExtra("dataItem")
//        window.attributes.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL
        if (tiketResults==null){

            try {
                val bitMatrix = multiFormatWriter.encode(
                    intent.getStringExtra("kd_buffer"),
                    BarcodeFormat.QR_CODE,
                    500,
                    400
                )
                val encoder = BarcodeEncoder()
                val bitmap = encoder.createBitmap(bitMatrix)
                imgqrcode.setImageBitmap(bitmap)
            } catch (e: WriterException) {
                e.printStackTrace()
            }

            if (intent.getStringExtra("jenis_px") == "2"){
                jenis_px="Umum"
            }
            else if (intent.getStringExtra("jenis_px") == "5"){
                jenis_px="BPJS"
            }
            else if (intent.getStringExtra("jenis_px") == "7"){
                jenis_px="Asuransi ("+intent.getStringExtra("asuransi_nama")+")"
            }
            tvkdantrian.text=("Kode Antrian "+intent.getStringExtra("kd_buffer"))

            tvnoantrian.text = "No Antrian "+intent.getStringExtra("no_antrian")
            tvklinik.text = intent.getStringExtra("nm_poli")
            tvdokter.text = intent.getStringExtra("nm_dokter")
            tv_tanggal.text = intent.getStringExtra("tanggal")+" ("+intent.getStringExtra("jam")+")"
            tvnmpasien.text = intent.getStringExtra("nm_px")
            tvrm.text = intent.getStringExtra("rm_px")
            tvcarabayar.text = jenis_px


        }
        else {
            val item=tiketResults as TiketResults?


           val kode_reg = if (item?.regBufferKode !=""){

               item?.regBufferKode.toString()

            }


           else{

               item.regBufferId.toString()


            }




                try {
                val bitMatrix = multiFormatWriter.encode(
                    kode_reg,
                    BarcodeFormat.QR_CODE,
                    400,
                    400
                )
                val encoder = BarcodeEncoder()
                val bitmap = encoder.createBitmap(bitMatrix)
                imgqrcode.setImageBitmap(bitmap)
            } catch (e: WriterException) {
                e.printStackTrace()
            }


            tvnoantrian.text =  "No Antrian "+item?.regBufferNoAntrian.toString()
            tvklinik.text = item?.poliNama.toString()
            tvdokter.text = item?.usrName.toString()
            tv_tanggal.text = item?.regBufferTanggal.toString()+" ("+ item?.regBufferWaktu.toString()+")"
            tvnmpasien.text = item?.custUsrNama.toString()
            tvrm.text = item?.custUsrKode.toString()
            tvcarabayar.text = item?.jenisNama.toString()+" ("+item?.perusahaanNama+" )"
            tvkdantrian.text=("Kode Antrian $kode_reg")


        }


    }

    override fun onGetTiket(data: List<TiketResults?>?) {

    }

    override fun onFailedGetTiket(msg: String) {

    }

    override fun onGet(msg: String) {

    }

    override fun onFailed(msg: String) {

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


}
