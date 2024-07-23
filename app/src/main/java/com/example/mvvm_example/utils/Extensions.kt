package com.example.mvvm_example.utils

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.mvvm_example.R

const val PROGRESS_BAR_WIDTH = 5f
const val PROGRESS_BAR_RADIUS = 30f

fun ImageView.loadImageFromUrl(imageUrl: String) {
    val circularProgressDrawable = CircularProgressDrawable(context).apply {
        colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, R.color.black), PorterDuff.Mode.SRC_IN)
        strokeWidth = PROGRESS_BAR_WIDTH
        centerRadius = PROGRESS_BAR_RADIUS
        start()
    }
    Glide
        .with(context)
        .load(imageUrl)
        .placeholder(circularProgressDrawable)
        .into(this)
}
