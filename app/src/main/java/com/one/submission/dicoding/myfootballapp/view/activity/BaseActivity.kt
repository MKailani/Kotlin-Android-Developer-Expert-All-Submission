package com.one.submission.dicoding.myfootballapp.view.activity

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.utils.espresso.EspressoIdlingResource
import com.one.submission.dicoding.myfootballapp.view.fragment.MatchFragment

/**
 * Dicoding Academy
 *
 * Final Project
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 10/02/19.
 */
abstract class BaseActivity : AppCompatActivity() {

    private fun popBackStack() {
        val backStackCount = supportFragmentManager.backStackEntryCount
        if (backStackCount - 2 >= 0) {
            var backStackName: String? = supportFragmentManager.getBackStackEntryAt(backStackCount - 2).name
            backStackName = backStackName ?: ""

            if (TextUtils.isEmpty(backStackName)) {
                goToHomeScreen()
            } else {
                supportFragmentManager.popBackStackImmediate()
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
        EspressoIdlingResource.increment()
        when {
            this is MatchDetailActivity ||
            this is MatchSearchActivity ||
            this is PlayersDetailActivity ||
            this is TeamsDetailActivity -> super.onBackPressed()
            else -> popBackStack()
        }
    }

    fun replaceFragment(fragment: Fragment, tagFragment: String?) {

        // Clear Backstack if user click Now Playing
        if (fragment is MatchFragment) {
            if (supportFragmentManager.backStackEntryCount - 2 >= 0) {
                goToTopFragment()
            }

        }
        if (!TextUtils.isEmpty(tagFragment))
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_screen, fragment)
                .addToBackStack(tagFragment)
                .commit()
        else
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_screen, fragment)
                .addToBackStack(null)
                .commit()
    }

    private fun goToTopFragment() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun getCurrentFragment(): Fragment? = supportFragmentManager.findFragmentById(R.id.fl_screen)

    override fun onResume() {
        // Idling Resource Espresso Decrement
        EspressoIdlingResource.decrement()
        super.onResume()
    }
}