package com.one.submission.dicoding.myfootballapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Dicoding Academy
 *
 * Submission 2
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 04/02/19.
 */
class Utils {

    // Static Function
    companion object {
        // Load Image for glide
        fun loadImage(context: Context, viewTarget: ImageView, pathImage:String){
            Glide.with(context)
                .load(pathImage)
                .into(viewTarget)
        }
    }
}