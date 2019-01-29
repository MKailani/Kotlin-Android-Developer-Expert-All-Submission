package com.one.submission.dicoding.myfootballapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by kheys on 30/01/19.
 *
 */
class Utils {
    companion object {
        fun loadImage(viewTarget: ImageView, pathImage:Int?){
            Glide.with(viewTarget.context)
                .load(pathImage)
                .into(viewTarget)
        }
    }
}