package com.leonett.epoxysample.util

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

fun View.setRoundCorners(radiusRes: Int) {
    this.clipToOutline = true
    this.outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline?) {
            outline?.setRoundRect(
                    0,
                    0,
                    view!!.width,
                    view.height,
                    view.context.resources.getDimension(radiusRes)
            )
        }
    }
    this.clipToOutline = true
}