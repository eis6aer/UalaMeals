package com.matiaslev.ualatest.presentation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.matiaslev.ualatest.R
import kotlinx.android.synthetic.main.uala_animated_text.view.a1
import kotlinx.android.synthetic.main.uala_animated_text.view.a2
import kotlinx.android.synthetic.main.uala_animated_text.view.l
import kotlinx.android.synthetic.main.uala_animated_text.view.u

class CustomUalaAnimatedText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.uala_animated_text, this)

        val uAnimation = ObjectAnimator.ofFloat(u, "translationX", -700f).apply {
            duration = 2000
        }
        val uRotation = ObjectAnimator.ofFloat(u, "rotation", 0f, 360f).apply {
            duration = 2000
        }
        val uAlpha = ObjectAnimator.ofFloat(u, "alpha", 0f, 1f).apply {
            duration = 2000
        }


        val a1Animation = ObjectAnimator.ofFloat(a1, "translationX", -600f).apply {
            duration = 2000
        }
        val a1Rotation = ObjectAnimator.ofFloat(a1, "rotation", 0f, 360f).apply {
            duration = 2000
        }
        val a1Alpha = ObjectAnimator.ofFloat(a1, "alpha", 0f, 1f).apply {
            duration = 2000
        }

        val lAnimation = ObjectAnimator.ofFloat(l, "translationX", -500f).apply {
            duration = 2000
        }
        val lRotation = ObjectAnimator.ofFloat(l, "rotation", 0f, 360f).apply {
            duration = 2000
        }
        val lAlpha = ObjectAnimator.ofFloat(l, "alpha", 0f, 1f).apply {
            duration = 2000
        }

        val a2Animation = ObjectAnimator.ofFloat(a2, "translationX", -400f).apply {
            duration = 2000
        }
        val a2Rotation = ObjectAnimator.ofFloat(a2, "rotation", 0f, 360f).apply {
            duration = 2000
        }
        val a2Alpha = ObjectAnimator.ofFloat(a2, "alpha", 0f, 1f).apply {
            duration = 2000
        }

        AnimatorSet().apply {
            playTogether(uAnimation, uRotation, uAlpha)
            play(a1Animation).with(a1Rotation).with(a1Alpha).after(uAnimation)
            play(lAnimation).with(lRotation).with(lAlpha).after(a1Animation)
            play(a2Animation).with(a2Rotation).with(a2Alpha).after(lAnimation)
            start()
        }
    }
}