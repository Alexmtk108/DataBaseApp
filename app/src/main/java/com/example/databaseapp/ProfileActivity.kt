package com.example.databaseapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.databaseapp.databinding.ActivityProfileBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id = intent.getIntExtra("id", -1)
        val db = AppDatabase.getBase(context = this)
        val personDao = db.personDao()
        lifecycleScope.launch(Dispatchers.IO) {
            val person = personDao.getPersonById(id.toLong())
            withContext(Dispatchers.Main) {
                binding.profileName.text = "Name: ${person.name}"
                binding.profileAge.text = "Name: ${person.age}"
                binding.profileJob.text = "Name: ${person.job}"
            }
        }
        binding.profileName.text =id.toString()
    }
}