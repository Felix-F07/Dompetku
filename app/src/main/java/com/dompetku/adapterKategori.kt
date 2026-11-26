package com.dompetku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dompetku.databinding.ItemKategoriBinding

class adapterKategori(
    private val listKategori: ArrayList<dcKategori>
) : RecyclerView.Adapter<adapterKategori.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemKategoriBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        val kategori = listKategori[position]

        holder.binding.tvNamaKategori.text = kategori.nama
        holder.binding.tvJenisKategori.text = kategori.jenis
    }

    override fun getItemCount(): Int {
        return listKategori.size
    }

    inner class ListViewHolder(
        val binding: ItemKategoriBinding
    ) : RecyclerView.ViewHolder(binding.root)
}