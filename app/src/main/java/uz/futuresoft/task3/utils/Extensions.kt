package uz.futuresoft.task3.utils

import android.content.Context
import android.net.Uri
import android.view.animation.Animation
import android.widget.ImageView
import com.bumptech.glide.Glide

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

fun ImageView.loadImage(context: Context, image: Uri) {
    Glide.with(context).load(image).into(this)
}