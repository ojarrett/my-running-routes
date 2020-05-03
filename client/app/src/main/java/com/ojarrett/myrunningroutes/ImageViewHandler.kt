package com.ojarrett.myrunningroutes

import android.view.View
import android.widget.ImageView

open class ImageViewHandler {
    var view: ImageView? = null

    public fun setImageView(imgView: ImageView) {
        view = imgView
    }
    public fun changeImage(srcImageResId: Int) {
        view?.setImageResource(srcImageResId)
        view?.setTag(srcImageResId)
    }
}