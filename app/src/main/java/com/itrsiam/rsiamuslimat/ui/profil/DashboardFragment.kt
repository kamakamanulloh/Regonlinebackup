package com.itrsiam.rsiamuslimat.ui.profil

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itrsiam.rsiamuslimat.R
import com.itrsiam.rsiamuslimat.api.Utils
import com.itrsiam.rsiamuslimat.login.LoginPresenter
import com.itrsiam.rsiamuslimat.login.LoginView
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class DashboardFragment() : Fragment(),LoginView, Parcelable {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var presenter: LoginPresenter
    var kata_sandi: String? =null
    var sharedpreferences: SharedPreferences? = null
    private val PREF_NAME = "my_shared_preferences"


    constructor(parcel: Parcel) : this() {
        kata_sandi = parcel.readString()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
       
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter= LoginPresenter(this)
        presenter.profil(Utils.user_id.toString())

        txtubah.onClick {
            val intent= Intent(context, EditProfilActivity::class.java)
            intent.putExtra("profil_id",Utils.user_id)
            intent.putExtra("username",tvusername.text.toString())
            intent.putExtra("nm_belakang",tv_nm_belakang.text.toString())
            intent.putExtra("nm_depan",tvnm_depan.text.toString())
            intent.putExtra("no_telp",tv_telp.text.toString())
            startActivity(intent)
        }
        btn_sandi.onClick {
            val intent= Intent(context, EditPaswordActivity::class.java)
            intent.putExtra("username",kata_sandi)
            startActivity(intent)
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
        tvusername.text=username.toString()
        tv_nm_belakang.text=nama_belakang.toString()
        tvnm_depan.text=nama_depan.toString()
        tv_telp.text=nohp.toString()
        kata_sandi=password.toString()

    }

    override fun onFailedLogin(msg: String?) {

    }

    override fun onFailure(msg: String?) {

    }

    override fun onSuccessUpdate(msg: String) {

    }

    override fun onErrorUpdate(msg: String) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(kata_sandi)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DashboardFragment> {
        override fun createFromParcel(parcel: Parcel): DashboardFragment {
            return DashboardFragment(parcel)
        }

        override fun newArray(size: Int): Array<DashboardFragment?> {
            return arrayOfNulls(size)
        }
    }
}
