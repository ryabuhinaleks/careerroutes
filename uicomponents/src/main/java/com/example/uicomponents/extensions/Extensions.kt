package com.example.uicomponents.extensions

import android.content.Context
import android.view.View
import androidx.annotation.DimenRes

fun View.setVisible(visible: Boolean = true) {
    this.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun View.setGone(gone: Boolean = true) {
    this.visibility = if (gone) View.GONE else View.VISIBLE
}

fun Context.toPixelSize(@DimenRes resId: Int): Int {
    return resources.getDimensionPixelSize(resId)
}