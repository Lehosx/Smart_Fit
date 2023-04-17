package com.lehos.musicplayer

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.lehos.musicplayer.databinding.ActivityHomeBinding
import com.lehos.musicplayer.service.StepDetectorService

class HomeActivity : AppCompatActivity(), stepsCallback {
    private var binding: ActivityHomeBinding? = null
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
//        binding!!.welcomeText.setText()
        binding!!.welcomeText.text = FirebaseAuth.getInstance().currentUser!!.displayName


        if(ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            requestPermissions(arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION), 0);
        }

        val requestPermissionLauncher =
            registerForActivityResult( ActivityResultContracts.RequestPermission() )
            { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            }
        val intent = Intent(this, StepDetectorService::class.java)
        startService(intent)

        if(
        ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED)  {

        }else{
           requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
        StepDetectorService.subscribe.register(this)
    }

    override fun subscribeSteps(steps: Int) {
        binding!!.TVSTEPS.setText(steps.toString());
        binding!!.TVCALORIES.setText(GeneralHelper.getCalories(steps));
        binding!!.TVDISTANCE.setText(GeneralHelper.getDistanceCovered(steps));
    }

    fun home_fitness_btn(view: View?) {
        val intent = Intent(this@HomeActivity, Fitness_home::class.java)
        startActivity(intent)
    }

    fun home_music_btn(view: View?) {
        val intent = Intent(this@HomeActivity, MainActivity::class.java)
        startActivity(intent)
    }

    fun home_social_btn(view: View?) {
        val intent = Intent(this@HomeActivity, Social_home::class.java)
        startActivity(intent)
    }



    override fun onBackPressed() {
        finishAffinity()
    }

}