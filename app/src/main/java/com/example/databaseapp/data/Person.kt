package com.example.databaseapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val name: String,
    val age: Int,
    val job: String,
    //val image: String
)