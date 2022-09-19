package com.example.trackingapp.ui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao() : CategoryDao

    companion object {



        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context : Context) : AppDatabase {

            val tempInstance = INSTANCE

            if (tempInstance != null){

                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Category_table"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }

}