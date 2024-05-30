package com.example.kovaltestapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kovaltestapp.model.Option


@Database(entities = [Option::class], version = 1)
abstract class OptionDb: RoomDatabase() {

    abstract fun optionDao(): OptionDao

    companion object {
        private var instance: OptionDb? = null

        fun getOptionDb(context: Context): OptionDb? {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    OptionDb::class.java,
                    "option.db"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}