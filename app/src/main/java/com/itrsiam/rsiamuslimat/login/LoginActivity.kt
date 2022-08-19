@file:Suppress("DEPRECATION")

package com.itrsiam.rsiamuslimat.login

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

import com.itrsiam.rsiamuslimat.HomeActivity
import com.itrsiam.rsiamuslimat.MainActivity
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.api.Utils.nohp
import com.itrsiam.rsiamuslimat.api.Utils.session
import com.itrsiam.rsiamuslimat.api.Utils.user_id
import com.itrsiam.rsiamuslimat.api.Utils.user_name
import com.itrsiam.rsiamuslimat.registrasi.RegistrasiActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.hideBtn
import kotlinx.android.synthetic.main.activity_login.showHideBtn

import kotlinx.android.synthetic.main.activity_main.btnlogin

import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity(),LoginView {
    private lateinit var presenter: LoginPresenter
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "my_shared_preferences"


    var session_status="session_status"

    private lateinit var progressDialog : ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)


        progressDialog = ProgressDialog(this)

        showHideBtn.onClick {
            edtpswd.transformationMethod= HideReturnsTransformationMethod.getInstance()
            hideBtn.isVisible=true
            showHideBtn.isVisible=false

        }

        hideBtn.onClick {
            edtpswd.transformationMethod= PasswordTransformationMethod.getInstance()
            showHideBtn.isVisible=true
            hideBtn.isVisible=false
        }

        tv_regisrasi.onClick {
            startActivity<RegistrasiActivity>()
        }
        btnlogin.setOnClickListener(View.OnClickListener {
            val pswd = edtpswd.text.toString()
            val username = edtusername.text.toString()


            if (username.isEmpty()){
                edtusername.error = "Kolom Harus Diisi"
            }
            else if (pswd.isEmpty()){
                edtpswd.error = "Kolom Harus Diisi"
            }
            else{
                progressDialog.setMessage("Proses Login")
                progressDialog.show()
                presenter.login(username, pswd)
            }
        })
        val sharedPref=getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)

        session=sharedPref.getBoolean(session_status,false)
        user_id=sharedPref.getString("id",null)
        user_name=sharedPref.getString("username",null)
        nohp=sharedPref.getString("no_hp",null)
        if (session){
            val intent=Intent(this,HomeActivity::class.java)
            intent.putExtra("id",user_id)
            intent.putExtra("username",user_name)
            intent.putExtra("no_hp", nohp)
            startActivity(intent)
            finish()
        }else if (intent.getStringExtra("verif")==null){
            startActivity<MainActivity>()


        }






    }

    override fun onSuccessLogin(msg: String?, id: String?, username: String?, nohp: String?) {
        progressDialog = ProgressDialog(this)
        progressDialog.dismiss()


        alert {
            title= "Selamat Datang $username"
            message="Login Sukses"
        }.show()

        val sharedPref=getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)

        val editor=sharedPref.edit()
        editor.putBoolean(session_status, true)
        editor.putString("id",id)
        editor.putString("username",username)
        editor.putString("no_hp",nohp)
        editor.commit()
        val intent=Intent(this,HomeActivity::class.java)
        intent.putExtra("id",id)
        intent.putExtra("username",username)
        intent.putExtra("no_hp",nohp)
        startActivity(intent)
        finish()
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
        progressDialog = ProgressDialog(this)
        progressDialog.dismiss()

        alert {
            title="Information Login"
            message=msg.toString()
        }.show()
    }

    override fun onFailure(msg: String?) {
        alert {
            title="Information Login"
            message="Failure"
        }.show()
    }

    override fun onSuccessUpdate(msg: String) {

    }

    override fun onErrorUpdate(msg: String) {

    }
}



