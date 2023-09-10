package com.example.animationfirst

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
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

            /*
            First, you’re going to need some local variables to hold state that we will need in the ensuing code.
            Specifically, you’ll need:
                a reference to the star field ViewGroup (which is just the parent of the current star view).
                the width and height of that container (which you will use to calculate the end translation values for our falling stars).
                the default width and height of our star (which you will later alter with a scale factor to get different-sized stars).
             */

        // >> 1 - Get a reference to the parent container:
        val container = binding?.mainIvStar?.parent as ViewGroup

        // >> 2- Get the dimensions of the container:
        val containerW = container.width
        val containerH = container.height

            /*
            Create a new View to hold the star graphic.
            Because the star is a VectorDrawable asset, use an AppCompatImageView,
            which has the ability to host that kind of resource.
            Create the star and add it to the background container.
             */

        // >> 3- Create a new star (newStar) as an AppCompatImageView:
        val newStar = AppCompatImageView(this)
        newStar.setImageResource(R.drawable.ic_star)


        // >> 4- Get the dimensions of the image view:
        var starW: Float? = binding?.mainIvStar?.width?.toFloat()
        var starH: Float? = binding?.mainIvStar?.height?.toFloat()

        // >> 5- Set the layout parameters for the new star:
        newStar.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT)

        // >> 6- Add the new star to the container:
        container.addView(newStar)

            /*
            Set the size of the star.
             Modify the star to have a random size, from .1x to 1.6x of its default size.
              Use this scale factor to change the cached width/height values,
              because you will need to know the actual pixel height/width for later calculations.
             */

        // >> 7- Size and position the new star
        newStar.scaleX = Math.random().toFloat() * 1.5f + .1f
        newStar.scaleY = newStar.scaleX

        if (starH != null) {
            starH *= newStar.scaleY
        }

        if (starW != null) {
            starW *= newStar.scaleX
        }

        // starW = starW?.times(newStar.scaleX)
        // starH = starH?.times(newStar.scaleY)

            /*
            Now position the new star. Horizontally, it should appear randomly somewhere from the left edge to the right edge.
            This code uses the width of the star to position it
            from half-way off the screen on the left (-starW / 2) to half-way off the screen on the right
            (with the star positioned at (containerW - starW / 2). The vertical positioning of the star will be handled later in the actual animation code.
             */

        if (starW != null) {
            newStar.translationX = Math.random().toFloat() *
                    containerW - starW / 2
        }

            /*
            Creating animators for star rotation and falling

            The star should rotate as it falls downwards.
            You’ve already seen one way to animate two properties together,
            using PropertyValuesHolder, in the previous task on scaling.
             */

        // >> 8- Create two animations for the new star:

        // create two animators, along with their interpolators:
        val mover = ObjectAnimator.ofFloat(newStar, View.TRANSLATION_Y,
            -starH!!, containerH + starH!!
        )

            /*
            Running the animations in parallel with AnimatorSet

            >> Create the AnimatorSet and add the child animators to it (along with information to play them in parallel).
            The default animation time of 300 milliseconds is too quick to enjoy the falling stars,
            so set the duration to a random number between 500 and 2000 milliseconds,
            so stars fall at different speeds.
             */

        mover.interpolator = AccelerateInterpolator(1f)
        val rotator = ObjectAnimator.ofFloat(newStar, View.ROTATION,
            (Math.random() * 1080).toFloat())
        rotator.interpolator = LinearInterpolator()


        val set = AnimatorSet()
        set.playTogether(mover, rotator)

        // >> Set the duration of the animation:

        set.duration = (Math.random() * 1500 + 500).toLong()


        // >> 9 - Add an animation listener to remove the new star from the container when the animation ends:
            /*
            Once newStar has fallen off the bottom of the screen,
            it should be removed from the container.
            Set a simple listener to wait for the end of the animation and remove it.
            Then start the animation.
             */

        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                container.removeView(newStar)
            }
        })

        // >> 10- Start the animation:
        set.start()

    }

        override fun onDestroy() {
        super.onDestroy()

    }
}