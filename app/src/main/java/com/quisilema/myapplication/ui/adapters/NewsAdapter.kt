package com.quisilema.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.quisilema.myapplication.R
import com.quisilema.myapplication.data.network.entities.topNews.Data
import com.quisilema.myapplication.databinding.ItemTopNewsBinding
import com.quisilema.myapplication.ui.entities.NewsDataUI
import androidx.recyclerview.widget.ListAdapter

class NewsAdapter(
    private val onClickItem: (NewsDataUI) -> Unit,
    private val onDeleteItem: (Int) -> Unit
) :
    ListAdapter<NewsDataUI, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTopNewsBinding.bind(view)

        fun render(data: NewsDataUI,
                   onClickItem: (NewsDataUI) -> Unit,
                   onDeleteItem: (Int) -> Unit) {

            binding.txtTitleNews.text = data.name
            binding.txtUrlNews.text = data.url
            binding.txtDescNews.text = data.description
            binding.imgNews
                .load(data.image) {
                    placeholder(R.drawable.ic_launcher_background)
                }

            itemView.setOnClickListener {
                onClickItem(data)
            }

            binding.btnDelete.setOnClickListener {
                onDeleteItem(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(
            inflater.inflate(
                R.layout.item_top_news,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        holder.render(item, onClickItem, onDeleteItem)
    }
}