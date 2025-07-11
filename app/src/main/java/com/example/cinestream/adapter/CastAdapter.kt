package com.example.cinestream.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinestream.R
import com.example.cinestream.data.model.ResultCast

class CastAdapter(
    private val list: List<ResultCast>,
    private val onItemClick: (Int) -> Unit
): RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster = itemView.findViewById<ImageView>(R.id.imgPoster)
        val character = itemView.findViewById<TextView>(R.id.character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cast, parent, false) // Boleh reuse item_popular
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val item = list[position]
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${item.profile_path}")
            .into(holder.imgPoster)

        holder.character.text = item.character ?: "Tidak Diketahui"


        holder.itemView.setOnClickListener {
            item.id?.let { id -> onItemClick(id) }
        }
    }

    override fun getItemCount(): Int = list.size
}