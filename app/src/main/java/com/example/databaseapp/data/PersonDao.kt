package com.example.databaseapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert
    fun insertPerson(person: Person): Long

    @Query("SELECT * FROM person")
    fun getAllPerson(): List<Person>

    @Query("SELECT * FROM person WHERE id = :personId")
    fun getPersonById(personId: Long): Person

    @Delete
    fun deletePerson(person: Person)

    @Query("DELETE from person WHERE id = :personId")
    fun deletePersonById(personId: Long)

}