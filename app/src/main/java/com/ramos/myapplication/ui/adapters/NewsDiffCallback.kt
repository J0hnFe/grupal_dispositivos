package com.ramos.myapplication.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.ramos.myapplication.ui.entities.NewsDataUI

class NewsDiffCallback : DiffUtil.ItemCallback<NewsDataUI>() {
    override fun areItemsTheSame(oldItem: NewsDataUI, newItem: NewsDataUI): Boolean {
  return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsDataUI, newItem: NewsDataUI): Boolean {
              return oldItem == newItem
    }
}