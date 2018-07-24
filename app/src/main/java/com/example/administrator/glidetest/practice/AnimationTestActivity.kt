package com.example.administrator.glidetest.practice

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.*
import com.example.administrator.glidetest.R
import kotlinx.android.synthetic.main.layout_animation.*
import java.lang.Math.*

/**
 * Created by moge on 2018/6/8.
 */
class AnimationTestActivity :AppCompatActivity(){


    companion object {
        fun launch(context: Context){
            val intent= Intent(context, AnimationTestActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_animation)

        animationTest()
    }

    private fun animationTest() {
        alpha?.setOnClickListener{
            val alphaAni= AlphaAnimation(1f,0f)
            alphaAni.duration=2000
            image?.startAnimation(alphaAni)
        }

        rotate?.setOnClickListener{
            val rotateAni = RotateAnimation(0f, -180f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 1f);
            rotateAni.duration=2000
            image?.startAnimation(rotateAni)
//            val rotateAnimator = ObjectAnimator.ofFloat(image,"rotationX", 0f,360f)
//            rotateAnimator.duration = 1000
//            rotateAnimator.start()
//            ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationX", -180, 0);
//            animator.setDuration(500).start();
        }

        scale?.setOnClickListener{
            val scaleAni = ScaleAnimation(0f,1f,0f,1f,
                    ScaleAnimation.RELATIVE_TO_SELF,0.5F,
                    ScaleAnimation.RELATIVE_TO_SELF,0.5F)
            scaleAni.duration=2000
            image?.startAnimation(scaleAni)
        }

        translate?.setOnClickListener{
            val translate = TranslateAnimation(0f,300f,0f,500f)
            translate.duration = 2000
            image?.startAnimation(translate)
        }

        `object`.setOnClickListener{
            val objectAni = ObjectAnimator.ofFloat(image, "translationX", 0f, 360f)
            objectAni.duration=2000
            objectAni.start()
        }

        spring.setOnClickListener {
            val objectAni1=ObjectAnimator.ofFloat(image,"scaleX",0.6f,1.0f)
            val objectAni2=ObjectAnimator.ofFloat(image,"scaleY",0.6f,1.0f)

            val animatorSet= AnimatorSet()
            animatorSet.duration=2000
            animatorSet.interpolator=SpringScaleInterpolator(0.4f)
            animatorSet.playTogether(objectAni1,objectAni2)
            animatorSet.start()
            animatorSet.addListener(object :Animator.AnimatorListener{
                override fun onAnimationEnd(animation: Animator?) {

                }

                override fun onAnimationCancel(animation: Animator?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onAnimationStart(animation: Animator?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onAnimationRepeat(animation: Animator?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }

            )
        }
    }


    class SpringScaleInterpolator(private var factor: Float) :Interpolator{

        override fun getInterpolation(input: Float): Float {
            val a:Int=2
            val b:Double= a.toDouble()

            return (Math.pow(b, (-10 * input).toDouble()) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1).toFloat()
        }
    }
}