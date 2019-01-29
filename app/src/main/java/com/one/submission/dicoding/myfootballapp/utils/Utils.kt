package com.one.submission.dicoding.myfootballapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Dicoding Academy
 *
 * Submission 1
 * Kotlin Android Developer Expert (MADE)
 *
 * Created by kheys on 30/01/19.
 */
class Utils {

    // Static Function
    companion object {
        // Load Image for glide
        fun loadImage(viewTarget: ImageView, pathImage:Int?){
            Glide.with(viewTarget.context)
                .load(pathImage)
                .into(viewTarget)
        }
    }
}