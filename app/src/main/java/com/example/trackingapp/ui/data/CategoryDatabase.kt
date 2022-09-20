package com.example.trackingapp.ui.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Money::class], version = 1)
abstract class CategoryDatabase : RoomDatabase() {

    abstract fun categoryDao() : CategoryDao

    companion object {
        @Volatile
        private var INSTANCE : CategoryDatabase? = null

        fun getDatabase(context : Context): CategoryDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CategoryDatabase::class.java,
                    "money_table"
                ).build()

                INSTANCE = instance

                return instance
            }
        }


    }

}