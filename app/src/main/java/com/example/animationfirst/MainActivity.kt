package com.example.animationfirst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animationfirst.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //

        binding?.mainBtnRotate?.setOnClickListener {

        }
        //
        binding?.mainBtnTranslate?.setOnClickListener {

        }
        //
        binding?.mainBtnScale?.setOnClickListener {

        }
        //
        binding?.mainBtnFade?.setOnClickListener {

        }
        //
        binding?.mainBtnColorize?.setOnClickListener {

        }
        //
        binding?.mainBtnShower?.setOnClickListener {

        }
        //

    }

    override fun onDestroy() {
        super.onDestroy()

    }
}