package com.basri.newsapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basri.newsapp.adapter.AdapterNews
import com.basri.newsapp.model.ResponseNews
import com.basri.newsapp.network.ApiRetrofit
import com.basri.newsapp.network.GitHubService
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory.create

class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewManager = GridLayoutManager(this, 1)

        var service = ApiRetrofit().getClient()?.create(GitHubService::class.java)

        ///get data news
        val getDataNews: Call<ResponseNews> = service!!.getNews(
                "Covid",
                "2020-09-19",
                "publishedAt",
                "1be7aa53092b438988bf68f3847fe127"
        )

        pb_data.visibility = View.VISIBLE
        getDataNews.enqueue(object :Callback<ResponseNews> {
            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                //untuk ambil data error
                Log.e("TAG","Data error ${t.message}")

                pb_data.visibility = View.GONE
                Toast.makeText(this@MainActivity,
                        "Gagal ambil data ${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                //dapatkan data berhasil
                if (response.isSuccessful){

                    pb_data.visibility = View.GONE
                        Log.e("TAG","data berhasil ${Gson().toJson(response.body()?.articles)}")
                    //cara set data ke adapater

                    var dataNews = response.body()?.articles

                    val adapterNews = dataNews?.let { AdapterNews(it) }
                    rv_news.setHasFixedSize(true)
                    rv_news.layoutManager = GridLayoutManager(this@MainActivity,1)
                    rv_news.adapter = adapterNews




                }

            }
        })



    }
}