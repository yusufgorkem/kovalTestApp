package com.example.kovaltestapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "option")
class Option (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "image")
    val image: Int,
    @ColumnInfo(name = "head")
    val head: Int,
    @ColumnInfo(name = "body")
    val body: Int,
)