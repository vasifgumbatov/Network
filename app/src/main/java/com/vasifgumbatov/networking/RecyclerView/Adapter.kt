package com.vasifgumbatov.networking.RecyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vasifgumbatov.networking.Data.PostsResponse
import com.vasifgumbatov.networking.databinding.ItemsBinding

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val dataList = mutableListOf<PostsResponse>()

    fun addData(newList: List<PostsResponse>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()

    }

    class ViewHolder(private val binding: ItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PostsResponse) {
            binding.titleText.text = item.title
            binding.bodyText.text = item.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(dataList[position])
    }
}