package com.example.uicomponents.topbar

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.example.uicomponents.R
import com.example.uicomponents.extensions.setGone
import com.example.uicomponents.extensions.setVisible

class TopBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val titleView: TextView
    private val imageViewView: ImageView

    init {
        inflate(context, R.layout.view_top_bar, this)

        titleView = findViewById(R.id.TopBarTitle)
        imageViewView = findViewById(R.id.TopBarIcon)

        context.withStyledAttributes(attrs, R.styleable.TopBarView, defStyleAttr) {
            getString(R.styleable.TopBarView_title).run(::setTitle)
        }
    }

    fun setTitle(text: CharSequence?) {
        titleView.text = text
        titleView.setGone(text?.isEmpty() ?: true)
    }

    fun setRightIcon(type: TopBarIcon, listener: () -> Unit) = with(imageViewView) {
        when (type) {
            TopBarIcon.FILTER -> {
                setVisible()
                setImageResource(R.drawable.ic_filter_list)
            }
            else -> setGone()
        }
        setOnClickListener { listener() }
    }
}

enum class TopBarIcon {
    NONE,
    FILTER
}
