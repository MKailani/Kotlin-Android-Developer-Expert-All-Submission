package com.one.submission.dicoding.myfootballapp.view.component

import android.content.Context
import android.view.Gravity
import android.view.View
import com.one.submission.dicoding.myfootballapp.R
import org.jetbrains.anko.*

/**
 * Created by kheys on 30/01/19.
 *
 */
class ItemFootballUI : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>) : View = with(ui) {
        linearLayout{
            lparams{
                    matchParent
                    wrapContent
                    margin = dimen(R.dimen.margin_5dp)
            }
            gravity = Gravity.CENTER_VERTICAL

            // Load Image
            imageView (R.drawable.img_barca){
                id = R.id.iv_icon
            }.lparams(100,100)

            // Title
            textView("Title"){
                id = R.id.tv_title

            }.lparams {
                matchParent
                matchParent
                marginStart = dimen(R.dimen.margin_5dp)
            }

        }
    }


}