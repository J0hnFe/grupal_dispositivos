package com.quisilema.myapplication.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.quisilema.myapplication.R
import com.quisilema.myapplication.data.network.entities.oneNews.OneNewsDataClass
import com.quisilema.myapplication.databinding.ActivityDetailItemBinding
import com.quisilema.myapplication.logic.usercases.GetOneNewsUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailItemBinding
    private var itemId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        intent.extras.let {
//            itemId=it?.getString("id").toString()
//        }
//
//
//
//        lifecycleScope.launch(Dispatchers.Main) {
//
//           val item=  withContext(Dispatchers.IO) {
//                getData()
//            }
//            if (item!= null){
//                binding.txtIdItem.text = item.title
//
//            }
//
//        }

    }

    suspend fun getData(): OneNewsDataClass? {
        var item: OneNewsDataClass? = null
        val x = GetOneNewsUserCase().invoke(itemId)
        x.onSuccess {
            item = it
        }
        x.onFailure {
            Log.d("API", "El llamado a la api ha fallado")
        }
        return item
    }


 fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.FavItem -> {
                val bottomSheetDialog = BottomSheetDialog(this)
                val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
                bottomSheetDialog.setContentView(view)
                bottomSheetDialog.show()
            }
            // maneja otros casos aquí...
        }
        return true
    }
}