package com.example.trackingapp.ui.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trackingapp.ui.data.converters.Converter
import com.example.trackingapp.ui.data.entity.Transaction


@Database(entities = [Transaction::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converter::class)
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun transactionDao() : TransactionDao

}