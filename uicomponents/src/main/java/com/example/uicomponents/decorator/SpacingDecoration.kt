package com.example.uicomponents.decorator

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import com.example.uicomponents.extensions.toPixelSize

private class SpacingDecoration(
    @DimenRes private val horizontalMarginRes: Int? = null,
    @DimenRes private val verticalMarginRes: Int? = null,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        outRect.set(0, 0, 0, 0)

        val context = parent.context
        val horizontalMargin = horizontalMarginRes?.run(context::toPixelSize) ?: 0
        val verticalMargin = verticalMarginRes?.run(context::toPixelSize) ?: 0

        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        val itemCount = state.itemCount

        outRect.left = horizontalMargin
        outRect.right = horizontalMargin

        val halfVertical = verticalMargin / 2
        outRect.top = halfVertical
        outRect.bottom = halfVertical

        if (position == 0) {
            outRect.top = verticalMargin
        }

        if (position == itemCount - 1) {
            outRect.bottom = verticalMargin
        }
    }
}

fun RecyclerView.addSpacingDecorationIfNeeded(
    @DimenRes horizontalMarginRes: Int? = null,
    @DimenRes verticalMarginRes: Int? = null
) {
    val hasDecoration = (0 until itemDecorationCount).any { i ->
        getItemDecorationAt(i) is SpacingDecoration
    }

    if (!hasDecoration) {
        addItemDecoration(SpacingDecoration(horizontalMarginRes, verticalMarginRes))
    }
}
