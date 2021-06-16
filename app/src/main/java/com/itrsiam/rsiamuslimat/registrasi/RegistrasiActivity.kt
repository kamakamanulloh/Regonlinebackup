package com.itrsiam.rsiamuslimat.registrasi

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.itrsiam.rsiamuslimat.MainActivity
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.api.Utils
import com.itrsiam.rsiamuslimat.login.LoginActivity
import kotlinx.android.synthetic.main.activity_registrasi.*
import org.jetbrains.anko.alert

@Suppress("DEPRECATION")
class RegistrasiActivity : AppCompatActivity(),RegisterView {
    private lateinit var presenter : RegisterPresenter
    private lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrasi)

        progressDialog = ProgressDialog(this)

        presenter = RegisterPresenter(this)
        btnregister.setOnClickListener(View.OnClickListener {
            val username=txtusername.text.toString()
            val nama_depan=txtnamadepan.text.toString()
            val nama_belakang=txtnamabelakang.text.toString()
            val pswd=txtregpswd.text.toString()
            val no_hp=txtnohp.text.toString()





            if (username.isEmpty()){
                txtusername.setError("Kolom Harus Diisi")
            }
            else if (nama_belakang.isEmpty()){
                txtnamabelakang.setError("Kolom Harus Diisi")
            }
            else if (nama_depan.isEmpty()){
                txtnamadepan.setError("Kolom Harus Diisi")
            }
            else if (pswd.isEmpty()){
                txtregpswd.setError("Kolom Harus Diisi")
            }
            else if (no_hp.isEmpty()){
                txtnohp.setError("Kolom Harus Diisi")
            }
            else{
                progressDialog.show()
                presenter.register(username,nama_depan,nama_belakang,pswd,no_hp)

            }
        })
    }

    override fun onSuccessRegister(msg: String?) {

        progressDialog.dismiss()

//        alert {
//            title="Information Register"
//            message="Registrasi Berhasil Silahkan Login"
//        }.show()
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Registrasi Akun Berhasil Apakah Anda Ingin Login ?")
            .setPositiveButton(R.string.ya,
                DialogInterface.OnClickListener { dialog, id ->
                    // FIRE ZE MISSILES!
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()

                })
            .setNegativeButton(R.string.tidak,
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog

                    dialog.dismiss()
                })
        // Create the AlertDialog object and return it
        builder.create()
        builder.show()

    }

    override fun onFailedRegister(msg: String?) {

        progressDialog.dismiss()

        alert {
            title="Information Register"
            message=msg.toString()
        }.show()

    }
}
