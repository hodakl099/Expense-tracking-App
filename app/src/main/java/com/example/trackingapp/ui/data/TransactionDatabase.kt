package com.example.trackingapp.ui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trackingapp.ui.repository.TransactionRepository

@Database(entities = [Transaction::class],
    version = 1,
    exportSchema = false,
    )
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun transactionDao() : TransactionDao

    companion object {
        @Volatile
        private var INSTANCE : TransactionDatabase? = null

        fun getDatabase(context : Context): TransactionDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransactionDatabase::class.java,
                    "all_transactions"
                ).build()

                INSTANCE = instance

                return instance
            }
        }


    }

}