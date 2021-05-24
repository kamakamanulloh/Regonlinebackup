package com.itrsiam.rsiamuslimat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.itrsiam.rsiamuslimat.api.Utils
import com.itrsiam.rsiamuslimat.ui.home.HomeFragment

class HomeActivity : AppCompatActivity() {
   public var username: String? = null
    public var id: String? = null
    private lateinit var appUpdateManager: AppUpdateManager
    private lateinit var listener: InstallStateUpdatedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard))
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val intent = intent
        Utils.user_name=intent.getStringExtra("username").toString()
        Utils.user_id=intent.getStringExtra("id").toString()
        Utils.nohp=intent.getStringExtra("no_hp").toString()
        val bundle = Bundle()

        bundle.putString("username",username)
        bundle.putString("id",id)
        val fragInfo = HomeFragment()
        fragInfo.setArguments(bundle)
//            alert {
//                title= (username as String?).toString()+id
//            }.show()

        appUpdateManager = AppUpdateManagerFactory.create(this)
        listener = InstallStateUpdatedListener { state ->
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                showInfoUpdateSuccess()
            }
        }
        appUpdateManager.registerListener(listener)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener {
            if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && it.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    it,
                    AppUpdateType.FLEXIBLE,
                    this,
                    999
                )
            } else {
                // TODO: do something in here if update not available
            }
        }


    }

    override fun onResume() {
        appUpdateManager.appUpdateInfo
            .addOnSuccessListener {
                if (it.installStatus() == InstallStatus.DOWNLOADED) {
                    showInfoUpdateSuccess()
                } else if (it.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                    appUpdateManager.startUpdateFlowForResult(
                        it,
                        AppUpdateType.FLEXIBLE,
                        this,
                        999
                    )
                }
            }
        super.onResume()
    }

    private fun showInfoUpdateSuccess() {
        Snackbar.make(findViewById(android.R.id.content), "Restart to update", Snackbar.LENGTH_INDEFINITE)
            .setAction("Restart") {
                appUpdateManager.completeUpdate()
                appUpdateManager.unregisterListener(listener)
            }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        appUpdateManager.unregisterListener(listener)
    }



}
