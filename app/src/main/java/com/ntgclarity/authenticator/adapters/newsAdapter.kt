package com.ntgclarity.authenticator.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ntgclarity.authenticator.R

class newsAdapter(private var images:List<String>,
                  private var titles:List<String>,
                  private var description:List<String>,
                  private var urls:List<String>
                  ):RecyclerView.Adapter<newsAdapter.newsViewHolder>() {

    inner class newsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val itemTitle: TextView = itemView.findViewById(R.id.articleTitle)
        val itemDetail: TextView = itemView.findViewById(R.id.artickedescription)
        val itemPicture: ImageView = itemView.findViewById(R.id.articlePic)

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
//***********for urls
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(urls[position])
                startActivity(itemView.context, intent,null)
            }
        }
    }
    //******************first
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.article, parent, false)
        return newsViewHolder(v)
    }

    //******************second
    override fun onBindViewHolder(holder: newsViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = description[position]

        Glide.with(holder.itemPicture)
            .load(images[position])
            .into(holder.itemPicture)    }

    override fun getItemCount(): Int {
        return titles.size    }




}