package com.example.trackingapp.ui.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity(tableName = "all_transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "amount")
    var amount : String,
    @ColumnInfo(name = "category")
    var category : String,
    @ColumnInfo(name = "transactionType")
    var transactionType : String,
    @ColumnInfo(name = "date")
    var date : String,
    @ColumnInfo(name = "payment")
    var payment : String,
    @ColumnInfo(name = "note")
    var note : String,
    @ColumnInfo(name = "timeAt")
    var timeAt : Long = System.currentTimeMillis()
) {
    val createdAtDataFormat : String
    get() = DateFormat.getDateTimeInstance().format(timeAt)
}


