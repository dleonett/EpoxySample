package com.leonett.photofeed.ui.util

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