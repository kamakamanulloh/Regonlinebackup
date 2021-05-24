package com.itrsiam.rsiamuslimat.registrasi

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import com.itrsiam.rsiamuslimat.MainActivity
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.activity_registrasi.*
import org.jetbrains.anko.alert

@Suppress("DEPRECATION")
class RegistrasiActivity : AppCompatActivity(),RegisterView {
    private lateinit var presenter : RegisterPresenter
    private lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrasi)
        ButterKnife.bind(this)

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

        alert {
            title="Information Register"
            message="Registrasi Berhasil Silahkan Login"
        }.show()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    override fun onFailedRegister(msg: String?) {

        progressDialog.dismiss()

        alert {
            title="Information Register"
            message=msg.toString()
        }.show()

    }
}
