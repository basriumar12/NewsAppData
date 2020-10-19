package com.basri.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object{
        val KEY_TITLE = "TITLE"
        val KEY_DESK = "DESK"
        val KEY_IMAGE ="IMAGE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tv_title_news.text = intent.getStringExtra(KEY_TITLE)
        tv_deskripsi_news.text = intent.getStringExtra(KEY_DESK)
        Glide.with(this).load(
            intent.getStringExtra(KEY_IMAGE))
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(img_news);
    }
}