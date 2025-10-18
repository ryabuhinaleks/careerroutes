package com.example.uicomponents.cards.postCard

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.example.uicomponents.R

class PostCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val postTitleView: TextView
    private val postDescriptionView: TextView
    private val postFavoriteView: ImageView

    init {
        inflate(context, R.layout.view_post_card, this)

        postTitleView = findViewById(R.id.postTitle)
        postDescriptionView = findViewById(R.id.postDescription)
        postFavoriteView = findViewById(R.id.postFavorite)
    }

    fun setContent(
        title: String,
        description: String
    ) {
        postTitleView.text = title
        postDescriptionView.text = description
    }

    fun setFavoriteIcon(
        @DrawableRes res: Int,
        listener: () -> Unit
    ) = with(postFavoriteView) {
        setImageResource(res)
        setOnClickListener { listener() }
    }
}