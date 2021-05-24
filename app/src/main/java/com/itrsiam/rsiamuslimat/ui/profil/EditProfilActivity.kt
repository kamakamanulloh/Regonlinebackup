package com.itrsiam.rsiamuslimat.ui.profil

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itrsiam.rsiamuslimat.R
import kotlinx.android.synthetic.main.activity_edit_profil.*
import com.itrsiam.rsiamuslimat.api.Utils
import com.itrsiam.rsiamuslimat.login.LoginPresenter
import com.itrsiam.rsiamuslimat.login.LoginView
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
class EditProfilActivity : AppCompatActivity(),LoginView {
    private lateinit var presenter: LoginPresenter
    private lateinit var progressDialog : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)
        val intent=intent
        txtnamadepan.setText(intent.getStringExtra("nm_depan"))
        txtnamabelakang.setText(intent.getStringExtra("nm_belakang"))
        txtusername.setText(intent.getStringExtra("username"))
        txtnohp.setText(intent.getStringExtra("no_telp"))
        presenter = LoginPresenter(this)
        btnupdate.onClick {
            presenter.edit_profil(
                Utils.user_id.toString(),
                txtusername.text.toString(),
                txtnamadepan.text.toString(),
                txtnamabelakang.text.toString(),

                txtnohp.text.toString())
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
           message= msg
       }.show()
//        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun onErrorUpdate(msg: String) {
        alert {
            message= msg
        }.show()
    }
}
