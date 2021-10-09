package com.psi.toserbalist.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.psi.toserbalist.MainActivity
import com.psi.toserbalist.R
import com.psi.toserbalist.models.BarangLists
import com.psi.toserbalist.ui.main.Makanan

class BarangAdapter(private var barang: List<BarangLists>) :

    RecyclerView.Adapter<BarangAdapter.Holder>() {
    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var Image : ImageView = view.findViewById(R.id.Image)
        var Judul: TextView = view.findViewById(R.id.Judul)
        var Desc: TextView = view.findViewById(R.id.Desc)
    }

    // fungsi inisialisasi awal untuk menyiapkan layout yang akan digunakan
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = barang[position]
        holder.Judul.text = item.Title
        holder.Desc.text = item.Deskripsi
        Glide.with(holder.Image).load(item.Gambar).fitCenter().into(holder.Image)
    }

    override fun getItemCount(): Int {
        return barang.size
    }
}