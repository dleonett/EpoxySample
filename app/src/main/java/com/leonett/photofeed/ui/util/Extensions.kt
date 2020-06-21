package com.leonett.photofeed.ui.util

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.widget.TextView
import kotlin.math.ln
import kotlin.math.pow

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

fun TextView.addImageAtEnd(drawableResId: Int) {
    val ssb = SpannableStringBuilder("${this.text}  ")
    ssb.setSpan(
        ImageSpan(this.context, drawableResId, ImageSpan.ALIGN_BASELINE),
        ssb.length - 1,
        ssb.length,
        Spannable.SPAN_INCLUSIVE_INCLUSIVE
    )

    this.text = ssb
}