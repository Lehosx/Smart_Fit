package com.lehos.musicplayer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lehos.musicplayer.databinding.ActivityStepsScreenBinding
import com.lehos.musicplayer.service.StepDetectorService

class StepsScreen : AppCompatActivity()  {

    private  lateinit var  binding : ActivityStepsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStepsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this, StepDetectorService::class.java)
        startService(intent)

        StepDetectorService.subscribe.register(this)
    }

}