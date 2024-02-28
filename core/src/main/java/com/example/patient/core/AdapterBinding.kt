package com.example.patient.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
private fun imageUrl(view:ImageView, url:String?){
    view.load(url)
}