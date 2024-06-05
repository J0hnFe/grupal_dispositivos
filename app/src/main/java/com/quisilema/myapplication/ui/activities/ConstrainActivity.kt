package com.quisilema.myapplication.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.quisilema.myapplication.R
import com.quisilema.myapplication.data.network.entities.topNews.Data
import com.quisilema.myapplication.databinding.ActivityConstrainBinding
import com.quisilema.myapplication.logic.usercases.GetAllTopsNewUserCase
import com.quisilema.myapplication.ui.adapters.NewsAdapter
import com.quisilema.myapplication.ui.entities.NewsDataUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConstrainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConstrainBinding
    private  var items: MutableList<NewsDataUI> = mutableListOf()
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
        binding.bottomNavigation.setOnItemSelectedListener {
            item ->
            when(item.itemId){
                R.id.listarItem -> {
        startActivity(Intent(this, DetailItemActivity::class.java))
                    true
                }
                R.id.FavItem -> {

                    val bottomSheetDialog = BottomSheetDialog(this)
                    val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
                    bottomSheetDialog.setContentView(view)
                    bottomSheetDialog.show()
                    true

                }
                R.id.NoFavItem ->{
                    Snackbar.make(binding.refreshRV, "No fav item",Snackbar.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
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
                    newsAdapter.submitList(items) // Aquí es donde cambiamos a usar submitList
                }

                resultItems.onFailure {
                    Snackbar.make(binding.refreshRV, it.message.toString(), Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun descriptionItem(news: NewsDataUI  ) {

        Snackbar.make(binding.refreshRV, news.name,Snackbar.LENGTH_LONG).show()

//        Log.d("UUID", news.id)
//        val intent = Intent(this,
//            DetailItemActivity::class.java).apply {
//            putExtra("id", news.id)
//        }
//      startActivity(intent)

    }
    private fun deleteItem(position: Int) {
        items.removeAt(position)
        newsAdapter.submitList(items.toList()) // Aquí es donde cambiamos a usar submitList
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
        newsAdapter.submitList(items) // Aquí es donde cambiamos a usar submitList
    }
}
