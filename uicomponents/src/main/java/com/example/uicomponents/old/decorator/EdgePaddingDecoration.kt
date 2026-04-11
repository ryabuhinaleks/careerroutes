package com.example.uicomponents.old.decorator

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

private class EdgePaddingDecoration(
    @DimenRes private val topPaddingRes: Int? = null,
    @DimenRes private val bottomPaddingRes: Int? = null
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val resources = parent.context.resources
        val topPadding = topPaddingRes?.let { resources.getDimensionPixelSize(it) } ?: 0
        val bottomPadding = bottomPaddingRes?.let { resources.getDimensionPixelSize(it) } ?: 0

        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        if (position == 0) {
            outRect.top = topPadding
        }

        if (position == itemCount - 1) {
            outRect.bottom = bottomPadding
        }
    }
}

fun RecyclerView.addEdgePaddingDecoration(
    @DimenRes topPaddingRes: Int? = null,
    @DimenRes bottomPaddingRes: Int? = null
) {
    val hasDecoration = (0 until itemDecorationCount).any { i ->
        getItemDecorationAt(i) is EdgePaddingDecoration
    }

    if (!hasDecoration) {
        addItemDecoration(EdgePaddingDecoration(topPaddingRes, bottomPaddingRes))
    }
}
