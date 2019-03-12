package com.one.submission.dicoding.myfootballapp.utils

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.MenuItem
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.one.submission.dicoding.myfootballapp.R

/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
class Utils {

    // Static Function
    companion object {
        // Load Image for glide
        fun loadImage(context: Context, viewTarget: ImageView, pathImage: String) {
            Glide.with(context)
                .load(pathImage)
                .into(viewTarget)
        }

        /**
         * Change Menu Icon Drawable for Vector Drawable
         *
         * @param context used for call Context Compat
         * @param menuItem used for change menu item color.
         */
        fun menuIconDrawable(context: Context, menuItem: MenuItem?, isSelected: Boolean) {
            menuItem?.icon = ContextCompat.getDrawable(
                context,
                if (isSelected)
                    R.drawable.ic_fav_selected
                else
                    R.drawable.ic_fav_unselected
            )
        }
    }
}