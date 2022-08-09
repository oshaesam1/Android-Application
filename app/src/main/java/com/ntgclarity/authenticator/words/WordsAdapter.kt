package com.ntgclarity.authenticator.words

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import com.ntgclarity.authenticator.R

class WordsAdapter(private val words: Array<Word>) :
    RecyclerView.Adapter<WordsAdapter.WordViewHolder>() {
    class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvWord: TextView
        val ivPhoto: ImageView

        init {
            tvWord = view.findViewById(R.id.tv_word)
            ivPhoto = view.findViewById(R.id.iv_photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_word, parent, false)

        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = words[position]

        holder.tvWord.text = word.word1

        Glide.with(holder.itemView)
            .load(word.url)
            .into(holder.ivPhoto)
    }

    override fun getItemCount(): Int = words.size
}

data class Word(
    @SerializedName("word") val word1: String,
    val url: String)