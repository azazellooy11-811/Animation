package com.example.myanimationwithbutton

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.Scene
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {
    private var index = 0
    lateinit var animationHide: Animation
    lateinit var animationShow: Animation
    lateinit var viewGroup: ViewGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        animationShow = AnimationUtils.loadAnimation(this, R.anim.show_rotate)
        animationHide = AnimationUtils.loadAnimation(this, R.anim.rotate)

        btn_show_hide.setOnClickListener{
            onClick()
        }
        btn_2.setOnClickListener{
            onClick2()
        }
    }

    private fun onClick2() {
        val springAnimation = SpringAnimation(btn_2, DynamicAnimation.X)
        val springForce = SpringForce()
        springForce.finalPosition = btn_helper.x
        springForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
        springForce.stiffness = SpringForce.STIFFNESS_LOW
        springAnimation.spring = springForce
        springAnimation.setStartVelocity(2000f)
        springAnimation.start()
    }

    private fun onClick() {
        if (index == 0) {
            ObjectAnimator.ofFloat(btn_show_hide, "translationY", 400f).apply {
                duration = 1000
                start()
            }
            index = 1
            btn_2.startAnimation(animationShow)
            btn_2.alpha = 1F

        } else {
            ObjectAnimator.ofFloat(btn_show_hide, "translationY", 0f).apply {
                duration = 1000
                start()
            }
            index = 0
            btn_2.startAnimation(animationHide)

        }
    }
    }
