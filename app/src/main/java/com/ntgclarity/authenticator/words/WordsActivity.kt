package com.ntgclarity.authenticator.words

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.maximeroussy.invitrode.WordGenerator
import com.ntgclarity.authenticator.R
import com.ntgclarity.authenticator.network.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WordsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)

        //val layoutManager = LinearLayoutManager(this)
        //val layoutManager = GridLayoutManager(this, 2)
        val layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)

        val words = randomWords()
        val adapter = WordsAdapter(words)
        val rvWords: RecyclerView = findViewById(R.id.rv_words)

        rvWords.layoutManager = layoutManager
        rvWords.adapter = adapter

        requestWords()
    }

    private fun randomWords(): Array<Word> {
        val generator = WordGenerator()

        return Array(100) {
            val len = (10..50).random()
            val width = (100..150).random()
            val height = (100..150).random()

            Word(generator.newWord(len), "https://picsum.photos/${width}/${height}")
        }
    }

    private fun requestWords() {
        retrofit.words()
            .enqueue(object : Callback<List<Word>> {
                override fun onResponse(call: Call<List<Word>>, response: Response<List<Word>>) {
                    if (response.isSuccessful) {
                        Log.d("***", "onResponse ${response.body().toString()}")
                    } else {
                        Log.d("***", "onResponse ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<Word>>, t: Throwable) {
                    Log.d("***", "onFailure ${t.localizedMessage} ")
                }
            })
    }
}