package com.one.submission.dicoding.myfootballapp.view.activity

import android.os.Bundle
import com.one.submission.dicoding.myfootballapp.R
import com.one.submission.dicoding.myfootballapp.utils.Utils
import com.one.submission.dicoding.myfootballapp.view.activity.iview.DetailView
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by kheys on 30/01/19.
 *
 */
class DetailActivity : BaseActivity(), DetailView {

    companion object {
        const val EXTRA_IMAGE_PATH ="extra:image_path"
        const val EXTRA_TITLE ="extra:title"
        const val EXTRA_DESC ="extra:desc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        loadView()
    }

    override fun loadView() {
        val intent = intent
        Utils.loadImage(iv_icon,intent?.getIntExtra(EXTRA_IMAGE_PATH,-1))
        tv_title.text = intent?.getStringExtra(EXTRA_TITLE)
        tv_description.text = intent?.getStringExtra(EXTRA_DESC)
    }
}