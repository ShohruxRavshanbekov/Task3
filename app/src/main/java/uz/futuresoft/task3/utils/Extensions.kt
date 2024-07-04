package uz.futuresoft.task3.utils

import android.view.animation.Animation

fun Animation.onAnimationStart(onAnimationStart: () -> Unit) {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
            onAnimationStart()
        }

        override fun onAnimationEnd(animation: Animation?) {}

        override fun onAnimationRepeat(animation: Animation?) {}
    })
}

fun Animation.onAnimationEnd(onAnimationEnd: () -> Unit) {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {}

        override fun onAnimationEnd(animation: Animation?) {
            onAnimationEnd()
        }

        override fun onAnimationRepeat(animation: Animation?) {}
    })
}