package com.example.databaseapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.databaseapp.data.Person
import com.example.databaseapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getBase(context = this)
        val personDao = db.personDao()

        binding.addButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                if (validateFields()) {
                    val person = Person(
                        name = binding.nameEt.text.toString(),
                        age = binding.ageEt.text.toString().toInt(),
                        job = binding.jobEt.text.toString(),
                    )
                    personDao.insertPerson(person)
                    val allPerson = personDao.getAllPerson()
                    Log.d("TAG", allPerson.toString())
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity,"Запись добавлена",Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity,"Заполните все поля",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.deleteButton.setOnClickListener {
            if (binding.idEt.text.toString().isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    personDao.deletePersonById(binding.idEt.text.toString().toLong())
                }
                Toast.makeText(this@MainActivity,"Запись удалена",Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this@MainActivity,"Заполните поле ID",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validateFields():Boolean {
        return binding.nameEt.text.isNotEmpty()
                &&  binding.ageEt.text.isNotEmpty()
                && binding.jobEt.text.isNotEmpty()
    }
}