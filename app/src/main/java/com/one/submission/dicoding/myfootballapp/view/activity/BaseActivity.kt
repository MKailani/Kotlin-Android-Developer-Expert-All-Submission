package com.one.submission.dicoding.myfootballapp.view.activity

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.one.submission.dicoding.myfootballapp.R

/**
 * Dicoding Academy
 *
 * Submission 2
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 04/02/19.
 */
abstract class BaseActivity : AppCompatActivity(){

    private fun popBackStack() {
        val backstackCount = fragmentManager.backStackEntryCount
        if (backstackCount - 2 >= 0) {
            var backstackName: String? = fragmentManager.getBackStackEntryAt(backstackCount - 2).name
            backstackName = backstackName ?: ""

            if (TextUtils.isEmpty(backstackName)) {
                goToHomeScreen()
            } else {
                fragmentManager.popBackStackImmediate()
            }
        } else {
            goToHomeScreen()
        }
    }

    private fun goToHomeScreen() {
        val i = Intent(Intent.ACTION_MAIN)
        i.addCategory(Intent.CATEGORY_HOME)
        startActivity(i)
    }

    override fun onBackPressed() {
        if(this is DetailActivity)
            super.onBackPressed()
        else
            popBackStack()
    }


    fun replaceFragment(fragment: Fragment) = replaceFragment(fragment,null)


    private fun replaceFragment(fragment: Fragment, tagFragment:String?) {
        if(!TextUtils.isEmpty(tagFragment))
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_screen,fragment)
                .addToBackStack(tagFragment)
                .commit()
        else
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_screen,fragment)
                .addToBackStack(null)
                .commit()
    }
}