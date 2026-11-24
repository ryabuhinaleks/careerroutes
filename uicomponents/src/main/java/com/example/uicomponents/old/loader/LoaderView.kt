package com.example.uicomponents.old.loader

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.uicomponents.R

class LoaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_loader, this)
    }

    fun show() {
        visibility = VISIBLE
    }

    fun hide() {
        visibility = GONE
    }
}