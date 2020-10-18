package com.leonett.photofeed.util

import android.graphics.Outline
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

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

fun Int.prettyCount(): String {
    if (this < 10000) return String.format("%,d", this)
    val exp = (ln(this.toDouble()) / ln(1000.0)).toInt()
    return String.format("%.1f%c", this / 1000.0.pow(exp.toDouble()), "KMGTPE"[exp - 1])
        .replace(".0K", "k")
        .replace(".0M", "M")
}

fun Int.formatWithSeparators(): String {
    return String.format("%,d", this)
}

fun TextView.appendDrawable(drawableResId: Int) {
    val ssb = SpannableStringBuilder("${this.text}  ")
    ssb.setSpan(
        ImageSpan(this.context, drawableResId, ImageSpan.ALIGN_BASELINE),
        ssb.length - 1,
        ssb.length,
        Spannable.SPAN_INCLUSIVE_INCLUSIVE
    )

    this.text = ssb
}

fun String.toDate(): Date? {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    return SimpleDateFormat(pattern, Locale.US).parse(this)
}

fun Date.toShortFormat(): String {
    val pattern = "MMM dd"
    return SimpleDateFormat(pattern, Locale.US).format(this)
}