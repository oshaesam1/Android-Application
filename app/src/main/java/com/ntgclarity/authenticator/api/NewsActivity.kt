package com.ntgclarity.authenticator.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ntgclarity.authenticator.R
import com.ntgclarity.authenticator.adapters.newsAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.ntgclarity.authenticator.newsInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NewsActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView //for setting up recycle view
    lateinit var countdownTimer: CountDownTimer
    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<String>()
    private var urlsList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        makeRequest()
    }
    private fun makeRequest()
    {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val screen = findViewById<View>(R.id.v_Screen)
        progressBar.visibility= View.VISIBLE
        screen.visibility= View.VISIBLE
        val api = Retrofit.Builder()
             .baseUrl("https://api.currentsapi.services")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(newsInterface::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getallNews()

                for (article in response.news) {
                    Log.d("MainActivity", "Result + $article")
                    addToList(article.title, article.description, article.image, article.url)
                }

                withContext(Dispatchers.Main) {
                    setUpRecyclerView()
                   progressBar.visibility = View.GONE
                    screen.animate().apply { alpha(0f)
                        duration=3000 }.start()
                  //  screen.visibility= View.GONE
                }
            } catch (e: Exception) {
                Log.d("MainActivity", e.toString())

                }
            }

        }
    private fun addToList(title: String, description: String, image: String, url: String) {
        titlesList.add(title)
        descList.add(description)
        imagesList.add(image)
        urlsList.add(url)


    }

    private fun setUpRecyclerView() {
        recyclerView=findViewById(R.id.rv_news)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter= newsAdapter(imagesList,titlesList, descList,urlsList)}

    }

