package com.revifaturahman.infocinema.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.revifaturahman.infocinema.R
import com.revifaturahman.infocinema.data.model.ResultsItem

class PopularAdapter(
    private val list: List<ResultsItem>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<PopularAdapter.CarouselViewHolder>() {

    inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster = itemView.findViewById<ImageView>(R.id.imgPoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_popular, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val item = list[position]
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${item.poster_path}")
            .into(holder.imgPoster)

        holder.itemView.setOnClickListener {
            item.id?.let { id -> onItemClick(id) }
        }
    }

    override fun getItemCount(): Int = list.size
}