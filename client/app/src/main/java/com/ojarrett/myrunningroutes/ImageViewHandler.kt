package com.ojarrett.myrunningroutes

import android.view.View
import android.widget.ImageView

open class ImageViewHandler(val view: ImageView) {
    public fun changeImage(srcImageResId: Int) {
        view.setImageResource(srcImageResId)
        view.setTag(srcImageResId)
    }
}