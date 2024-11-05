package com.example.databaseapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databaseapp.databinding.ActivityPersonListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.editButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.getBase(context = this)
        val personDao = db.personDao()
        //val retrofit
        lifecycleScope.launch(Dispatchers.IO) {
            val listPerson = personDao.getAllPerson()
            withContext(Dispatchers.Main) {
                binding.rv.layoutManager = LinearLayoutManager(this@PersonListActivity, LinearLayoutManager.VERTICAL,
                    false)
                binding.rv.adapter = PersonRvAdapter(listPerson)
            }
        }
    }
}