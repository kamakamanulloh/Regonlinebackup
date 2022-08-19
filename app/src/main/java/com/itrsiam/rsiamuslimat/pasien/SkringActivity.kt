package com.itrsiam.rsiamuslimat.pasien

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.itrsiam.rsiamuslimat.MainActivity
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.activity_skring.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.*


class SkringActivity : AppCompatActivity(),PendaftaranView {
    private lateinit var presenter : PendaftaranPresenter
    private lateinit var progressDialog : ProgressDialog

    var poli_id: String? =null
    var jadwal_id: String? =null
    var tanggal: String? =null
    var pasienid: String? =null
    var dokterid: String? =null

    var sbatuk:String?=""
    var ssesak:String?=""
    var sdemam:String?=""
    var snyeritelan:String?=""
    var speriksasaja:String?=""
    var skontrolhamil:String?=""
    var skontrolpascarawat:String?=""
    var speriksasakit:String?=""
    var simunisasi:String?=""



    var skeluhan :String?=null
    var kd_buffer:String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skring)
        progressDialog = ProgressDialog(this)
        kd_buffer=getRandomString(4)
        alert {
            title="Informasi"
            message="Harap Isi Data dengan Benar "
        }.show()



        presenter= PendaftaranPresenter(this)
        var periksa=" "
        btnsimpan.onClick {
            progressDialog.setMessage("Proses Login")
            progressDialog.show()

            if (nyeritelan.isChecked){
                snyeritelan +="Nyeri Telan,"
            }
            if(periksasaja.isChecked){
                periksa +="Periksa Saja,"
            }
            if(kontrolhamil.isChecked){
                periksa +="Kontrol Hamil,"
            }
            if(kontrolpascarawat.isChecked){
                periksa +="Kontrol Paska Rawat,"
            }
            if(periksasakit.isChecked){
                periksa +="Periksa Sakit,"

            }
            if(imunisasi.isChecked){
                simunisasi +="Imunisasi,"
            }
            if (batuk.isChecked){
                sbatuk +="Batuk,"
            }
            if (sesak.isChecked){
                ssesak +="Sesak,"
            }
            if (demam.isChecked){
                sdemam +="Demam,"
            }

//            Toast.makeText(baseContext, "$sbatuk$sdemam$ssesak$snyeritelan dan $periksa",Toast.LENGTH_LONG).show()


                            presenter.regis_antrian(
                    intent.getStringExtra("dokter_id"),
                    intent.getStringExtra("jenis_px"),
                    intent.getStringExtra("user_id"),
                    intent.getStringExtra("poli_id"),
                    intent.getStringExtra("nm_dokter"),
                    intent.getStringExtra("nm_poli"),
                    intent.getStringExtra("no_bpjs"),
                    intent.getStringExtra("no_asuransi"),
                    intent.getStringExtra("asuransi_id"),
                    intent.getStringExtra("tanggal"),
                    intent.getStringExtra("jam"),
                    sbatuk+sdemam+ssesak+snyeritelan,
                    speriksasaja+speriksasakit+skontrolhamil+skontrolpascarawat+simunisasi,
                    txtkeluhan.text.toString().trim(),
                    txttujuanperiksa.text.toString().trim(),
                    intent.getStringExtra("pasien_id"),
                    intent.getStringExtra("no_rujukan"),
                    intent.getStringExtra("id_jadwal"),
                    intent.getStringExtra("no_hp"),
                   kd_buffer

//
                )






        }






    }




    companion object {
        private val ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm"
    }

    private fun getRandomString(sizeOfRandomString: Int): String {
        val random = Random()
        val sb = StringBuilder(sizeOfRandomString)
        for (i in 0 until sizeOfRandomString)
            sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
        return sb.toString()
    }


    override fun onSuccessPendaftaran(msg: String?,buffer_id:String?,noAntrian:String?) {

        progressDialog.dismiss()
//       alert {
//           title="Info"
//           message=msg.toString()
//       }.show()
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.dialogreg)
            .setPositiveButton(R.string.ya,
                DialogInterface.OnClickListener { _, _ ->
                    // FIRE ZE MISSILES!
                            val tiketintent= Intent(this, TicketViewActivity::class.java)
                            tiketintent.putExtra("buffer_id",buffer_id)
                            tiketintent.putExtra("no_antrian",noAntrian)
                            tiketintent.putExtra("nm_poli",intent.getStringExtra("nm_poli"))
                            tiketintent.putExtra("nm_dokter",intent.getStringExtra("nm_dokter"))
                            tiketintent.putExtra("jam",intent.getStringExtra("jam"))
                            tiketintent.putExtra("tanggal",intent.getStringExtra("tanggal"))
                            tiketintent.putExtra("jenis_px",intent.getStringExtra("jenis_px"))
                            tiketintent.putExtra("nm_px",intent.getStringExtra("nm_px"))
                            tiketintent.putExtra("rm_px",intent.getStringExtra("rm_px"))
                            tiketintent.putExtra("asuransi_nama",intent.getStringExtra("asuransi_nama"))
                            tiketintent.putExtra("kd_buffer",kd_buffer)
                            startActivity(tiketintent)


                })
            .setNegativeButton(R.string.tidak,
                DialogInterface.OnClickListener { dialog, _ ->
                    // User cancelled the dialog

                    startActivity(Intent(this, MainActivity::class.java))

                    dialog.dismiss()
                })
        // Create the AlertDialog object and return it
        builder.create()
        builder.show()


    }

    override fun onFailedRPendaftaran(msg: String?) {
        progressDialog.dismiss()
        alert {
            title="Info"
            message=msg.toString()
        }.show()
    }

    override fun onFullRPendaftaran(msg: String?) {
        progressDialog.dismiss()
        alert {
            title="Info"
            message=msg.toString()
        }.show()
    }


}
