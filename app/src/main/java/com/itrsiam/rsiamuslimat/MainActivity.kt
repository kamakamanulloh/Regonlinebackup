package com.itrsiam.rsiamuslimat

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.itrsiam.rsiamuslimat.api.Utils
import com.itrsiam.rsiamuslimat.login.LoginActivity
import com.itrsiam.rsiamuslimat.registrasi.RegistrasiActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnlogin.setOnClickListener(View.OnClickListener {
            val item="y"
            startActivity<LoginActivity>("verif" to item)
            finish()
        })
        btnregistrasi.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,RegistrasiActivity::class.java))

        })
        if (Utils.session){
            val intent=Intent(this,HomeActivity::class.java)
            intent.putExtra("id", Utils.user_id)
            intent.putExtra("username", Utils.user_name)
            startActivity(intent)
            finish()
        }


//
//        val isFirstRun =
//            getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
//                .getBoolean("isFirstRun", true)
//
//        if (isFirstRun) {
//            //show sign up activity
//            startActivity(Intent(this, LoginActivity::class.java))
////            Toast.makeText(RegistrasiActivity.this, "Run only once", Toast.LENGTH_LONG)
////                    .show();
//        }
//
//
//        getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit()
//            .putBoolean("isFirstRun", false).apply()

    }
}
