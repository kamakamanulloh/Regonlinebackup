package com.itrsiam.rsiamuslimat.ui.profil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itrsiam.rsiamuslimat.HomeActivity
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.api.Utils
import com.itrsiam.rsiamuslimat.login.LoginPresenter
import com.itrsiam.rsiamuslimat.login.LoginView
import kotlinx.android.synthetic.main.activity_edit_pasword.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick

class EditPaswordActivity : AppCompatActivity(),LoginView {
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pasword)
        presenter = LoginPresenter(this)
        btnupdatepswd.onClick {
            if (txtpswd.text.toString() == txtpswdd.text.toString()) {

                presenter.edit_password(
                    Utils.user_id.toString(),
                    intent.getStringExtra("password")
                )

            } else if (txtpswd.text.toString().isEmpty() && txtpswdd.text.toString().isEmpty()) {
                alert {
                    message = "Kata Sandi Harus Diisi"
                }.show()
            }
            else if (txtpswd.text.toString().isEmpty() != txtpswdd.text.toString().isEmpty()) {
                alert {
                    message = "Kata Sandi Harus Sama"
                }.show()
            }
        }
    }

    override fun onSuccessLogin(msg: String?, id: String?, username: String?, nohp: String?) {

    }

    override fun onProfil(
        id: String?,
        username: String?,
        nohp: String?,
        nama_depan: String?,
        nama_belakang: String?,
        password: String?
    ) {

    }

    override fun onFailedLogin(msg: String?) {

    }

    override fun onFailure(msg: String?) {

    }

    override fun onSuccessUpdate(msg: String) {
       alert {
           message="Berhasil"
       }.show()
        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun onErrorUpdate(msg: String) {
        alert {
            message= msg
        }.show()
    }
}