package com.ramos.myapplication.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ramos.myapplication.databinding.ActivityConstrainBinding
import com.ramos.myapplication.logic.usercases.GetAllTopsNewUserCase
import com.ramos.myapplication.ui.adapters.NewsAdapter
import com.ramos.myapplication.ui.entities.NewsDataUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConstrainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConstrainBinding
    private var items: MutableList<NewsDataUI> = mutableListOf()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstrainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initVariables()
        initListeners()
        initData()


    }

    private fun initVariables() {
        newsAdapter = NewsAdapter(
            { descriptionItem(it) },
            { deleteItem(it) })
        binding.rvTopNews.adapter = newsAdapter
//        binding.rvTopNews.layoutManager = LinearLayoutManager(
//            this, LinearLayoutManager.VERTICAL, false
//        )
        binding.rvTopNews.layoutManager = CarouselLayoutManager()
    }

    private fun initListeners() {
        binding.refreshRV.setOnRefreshListener {
            initData()
            binding.refreshRV.isRefreshing = false
        }

        binding.btnInsert.setOnClickListener {
            addItem()
        }
    }

    private fun initData() {
        binding.pgbarLoadData.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.IO) {

            val resultItems = GetAllTopsNewUserCase().invoke()
            withContext(Dispatchers.Main) {
                binding.pgbarLoadData.visibility = View.INVISIBLE

                resultItems.onSuccess {
                    items = it.toMutableList()
                    newsAdapter.itemList = items
                    newsAdapter.notifyDataSetChanged()
                }

                resultItems.onFailure {
                    Snackbar.make(binding.refreshRV, it.message.toString(), Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun descriptionItem(news: NewsDataUI) {
        Snackbar.make(binding.refreshRV, news.name, Snackbar.LENGTH_LONG).show()
//        Log.d("UUID", news.id)
//        val intent = Intent(this, DetailItemActivity::class.java).apply {
//            putExtra("id", news.id)
//        }
//        startActivity(intent)
    }

    private fun deleteItem(position: Int) {
        items.removeAt(position)
        newsAdapter.itemList = items
        newsAdapter.notifyItemRemoved(position)
    }

    private fun addItem() {
        items.add(
            NewsDataUI(
                "1",
                "www.google.com",
                "Noticia fake",
                "imagen_aleatoria",
                "description_fantasma",
                "ES"
            )
        )
        newsAdapter.itemList = items
        newsAdapter.notifyItemInserted(items.size - 1)
    }
}