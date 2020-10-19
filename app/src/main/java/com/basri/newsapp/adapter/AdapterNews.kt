package com.basri.newsapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.basri.newsapp.DetailActivity
import com.basri.newsapp.DetailActivity.Companion.KEY_DESK
import com.basri.newsapp.DetailActivity.Companion.KEY_IMAGE
import com.basri.newsapp.DetailActivity.Companion.KEY_TITLE
import com.basri.newsapp.R
import com.basri.newsapp.model.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_view.*
import kotlinx.android.synthetic.main.news_view.view.*

class AdapterNews (val listData : List<Article>)
    : RecyclerView.Adapter<AdapterNews.MyViewHolder>()
{


    class MyViewHolder(val view: View) :
        RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNews.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_view, parent, false) as View

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
        
    }

    override fun onBindViewHolder(holder: AdapterNews.MyViewHolder, position: Int) {
        holder.view.tv_title_news.text = listData[position].title
        holder.view.tv_deskripsi_news.text = listData[position].description
        Glide.with(holder.itemView.context).load(
            listData[position].urlToImage)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.itemView.img_news);

        holder.itemView.setOnClickListener {
            val kirimData = Intent(holder.itemView.context,
                DetailActivity::class.java
                )
            kirimData.putExtra(KEY_TITLE,listData[position].title)
            kirimData.putExtra(KEY_DESK,listData[position].description)
            kirimData.putExtra(KEY_IMAGE,listData[position].urlToImage)
            holder.itemView.context.startActivity(kirimData)
        }
    }
}