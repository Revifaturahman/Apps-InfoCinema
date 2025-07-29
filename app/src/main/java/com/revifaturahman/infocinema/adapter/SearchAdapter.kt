package com.revifaturahman.infocinema.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.revifaturahman.infocinema.R
import com.revifaturahman.infocinema.data.model.ResultsItem

class SearchAdapter(
    private val list: MutableList<ResultsItem>,
    private val onItemClick: (Int) -> Unit
): RecyclerView.Adapter<SearchAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster = itemView.findViewById<ImageView>(R.id.imgPoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = list[position]
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${item.poster_path}")
            .into(holder.imgPoster)

        holder.itemView.setOnClickListener {
            item.id?.let { id -> onItemClick(id) }
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<ResultsItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }


}