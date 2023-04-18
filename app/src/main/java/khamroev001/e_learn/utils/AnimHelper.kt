package khamroev001.e_learn.utils

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils

class AnimHelper private constructor() {

    companion object {
        private var instance: AnimHelper? = null
        fun newInstance(): AnimHelper {
            if (instance != null) return instance!!
            instance = AnimHelper()
            return instance!!
        }
    }

    fun animate(context: Context, view: View, anim: Int, endAction: EndAction) {
        val animation = AnimationUtils.loadAnimation(context, anim)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                endAction.endAction()
            }
        })
        view.startAnimation(animation)
    }
    fun animate(context: Context, view: View, anim: Int) {
        val animation = AnimationUtils.loadAnimation(context, anim)
        view.startAnimation(animation)
    }


    interface EndAction {
        fun endAction()
    }
}