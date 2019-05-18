package com.one.submission.dicoding.myfootballapp.utils

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.MenuItem
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.one.submission.dicoding.myfootballapp.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
object Utils {

    private const val DEFAULT_DATE = "yyyy-MM-dd"
    private const val DEFAULT_TIME = "HH:mm:ss"


    // Load Image for glide
    fun loadImage(context: Context, viewTarget: ImageView, pathImage: String) {
        if (pathImage.isNotEmpty()) {
            Glide.with(context)
                .load(pathImage)
                .into(viewTarget)
        }
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

    fun getDateFormat(date: String?): String {
        return formatDate(date.toString(), "EEE, dd MMM yyyy", true)
    }

    fun getTimeFormat(date: String?): String {
        return formatDate(date.toString(), "HH:mm a", false)
    }

    private fun formatDate(date: String, format: String, isDate: Boolean): String {
        val defaultFormatter = SimpleDateFormat(if (isDate) DEFAULT_DATE else DEFAULT_TIME, Locale.getDefault())
        var result = ""
        try {
            val parseFormatter = defaultFormatter.parse(date)
            val newFormat = SimpleDateFormat(format, Locale.getDefault())
            result = newFormat.format(parseFormatter)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return result
    }
}
