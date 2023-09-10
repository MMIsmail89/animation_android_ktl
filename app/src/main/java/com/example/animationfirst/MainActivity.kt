package com.example.animationfirst

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.animationfirst.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var star:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //
        star = findViewById(R.id.main_iv_star)

        binding?.mainBtnRotate?.setOnClickListener {
            rotate_fun()
        }
        //
        binding?.mainBtnTranslate?.setOnClickListener {
            move_fun()
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



    private fun ObjectAnimator.disableBtnViewDuringAnimation(mainBtnTranslate: Button) {
        addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                mainBtnTranslate.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                mainBtnTranslate.isEnabled = true
            }

        })
    }

    private fun rotate_fun() {

         val startAnimator : ObjectAnimator = ObjectAnimator.ofFloat(binding?.mainIvStar, View.ROTATION_Y, -360F, 0F)
        startAnimator.duration = 5000;
        //
        binding?.mainBtnRotate?.let { startAnimator.disableBtnViewDuringAnimation(it) }
        startAnimator.start()
    }

    private fun move_fun() {
        val startAnimator : ObjectAnimator = ObjectAnimator.ofFloat(binding?.mainIvStar, View.TRANSLATION_X, 300F)
        startAnimator.repeatCount = 31
        startAnimator.repeatMode = ObjectAnimator.REVERSE
        //
        binding?.mainBtnTranslate?.let { startAnimator.disableBtnViewDuringAnimation(it) }
        startAnimator.start()
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}