package com.example.trackingapp.ui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Money::class], version = 1)
abstract class MoneyDatabase : RoomDatabase() {

    abstract fun moneyDao() : MoneyDao

    companion object {
        @Volatile
        private var INSTANCE : MoneyDatabase? = null

        fun getDatabase(context : Context): MoneyDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoneyDatabase::class.java,
                    "money_table"
                ).build()

                INSTANCE = instance

                return instance
            }
        }


    }

}