package com.monstarlab.test.moviefinder.utils

import android.R
import android.animation.Animator
import android.widget.TextView
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Looper
import android.view.View
import com.monstarlab.test.moviefinder.utils.MyAppExecutors

class AnimationHelper {
    fun goneAnimation(context: Context, textView: TextView) {
        val shortAnimationDuration = context.resources.getInteger(R.integer.config_shortAnimTime)

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        textView.animate()
            .alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    textView.visibility = View.GONE
                }
            })
    }

    fun visibleAnimation(context: Context, textView: TextView) {
        val shortAnimationDuration = context.resources.getInteger(R.integer.config_shortAnimTime)

        // Animate the loading view to 100% opacity. After the animation ends,
        // set its visibility to VISIBLE as an optimization step (it won't
        // participate in layout passes, etc.)
        textView.animate()
            .alpha(1f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    textView.visibility = View.VISIBLE
                }
            })
    }
}