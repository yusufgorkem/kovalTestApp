package com.example.kovaltestapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kovaltestapp.model.Option

@Dao
interface OptionDao {
    @Insert
    fun insert(option: Option)

    @Delete
    fun delete(option: Option)

    @Query("SELECT*FROM option")
    fun getAllOptions(): List<Option>
}