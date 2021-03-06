package com.narinc.base.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.datepicker.MaterialDatePicker
import com.narinc.base.R


fun <S> MaterialDatePicker.Builder<S>.setSelection(pair: Pair<Long, Long>?) {
    if (pair != null) {
        setSelection(Pair(pair.first, pair.second))
    }
}

fun TextView.loadOrGone(dataText: String) {
    if (dataText.isNotEmpty())
        text = dataText
    else this.isGone = true
}

fun ImageView.loadImageOrDefault(imgUrl: String?) {
    if (imgUrl.isNullOrEmpty().not())
        Glide.with(context)
            .load(imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.anim_loading)
                    .error(R.drawable.ic_error)
            )
            .into(this)
    else
        this.setImageResource(R.drawable.ic_launcher_foreground)
}