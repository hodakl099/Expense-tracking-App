package com.example.trackingapp.ui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Transaction::class], version = 1,
    exportSchema = true,
    )
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun moneyDao() : AmountTransactionDao

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