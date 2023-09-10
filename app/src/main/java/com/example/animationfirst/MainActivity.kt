package com.example.animationfirst

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
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
            rotating()
        }
        //
        binding?.mainBtnTranslate?.setOnClickListener {
            moving()
        }
        //
        binding?.mainBtnScale?.setOnClickListener {
            sizing()
        }
        //
        binding?.mainBtnFade?.setOnClickListener {
            fading()
        }
        //
        binding?.mainBtnColorize?.setOnClickListener {
            coloring()
        }
        //
        binding?.mainBtnShower?.setOnClickListener {
            showingNewStart()
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

    private fun rotating() {

         val startAnimator : ObjectAnimator = ObjectAnimator.ofFloat(binding?.mainIvStar, View.ROTATION_Y, -360F, 0F)
        startAnimator.duration = 5000;
        //
        binding?.mainBtnRotate?.let { startAnimator.disableBtnViewDuringAnimation(it) }
        startAnimator.start()
    }

    private fun moving() {
        val startAnimator : ObjectAnimator = ObjectAnimator.ofFloat(binding?.mainIvStar, View.TRANSLATION_X, 300F)
        startAnimator.repeatCount = 31
        startAnimator.repeatMode = ObjectAnimator.REVERSE
        //
        binding?.mainBtnTranslate?.let { startAnimator.disableBtnViewDuringAnimation(it) }
        startAnimator.start()
    }

    private fun sizing() {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)

        val startAnimator : ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(binding?.mainIvStar, scaleX, scaleY)

        startAnimator.repeatCount = 1
        startAnimator.repeatMode = ObjectAnimator.REVERSE
        binding?.mainBtnScale?.let { startAnimator.disableBtnViewDuringAnimation(it) }

        startAnimator.start()

    }

    private fun fading() {
        val startAnimator = ObjectAnimator.ofFloat(binding?.mainIvStar, View.ALPHA, 0f)
        startAnimator.repeatCount = 15
        startAnimator.repeatMode = ObjectAnimator.REVERSE

        binding?.mainBtnFade?.let { startAnimator.disableBtnViewDuringAnimation(it) }

        startAnimator.start()
    }

    private fun coloring() {
        // var startAnimator = ObjectAnimator.ofInt(binding?.mainIvStar?.parent,"backgroundColor", Color.BLACK, Color.RED).start()

        var startAnimator = ObjectAnimator.ofArgb(binding?.mainIvStar?.parent,
            "backgroundColor", Color.BLACK, Color.RED)
        startAnimator.setDuration(500)
        startAnimator.repeatCount = 1
        startAnimator.repeatMode = ObjectAnimator.REVERSE

        binding?.mainBtnColorize?.let { startAnimator.disableBtnViewDuringAnimation(it) }

        startAnimator.start()

    }

    private fun showingNewStart() {
        val container = star.parent as ViewGroup
        val containerW = container.width
        val containerH = container.height
        var starW: Float = star.width.toFloat()
        var starH: Float = star.height.toFloat()
        //
        val newStar = AppCompatImageView(this)

        newStar.setImageResource(R.drawable.ic_star)
        newStar.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT)
        container.addView(newStar)
        //

    }

        override fun onDestroy() {
        super.onDestroy()

    }
}