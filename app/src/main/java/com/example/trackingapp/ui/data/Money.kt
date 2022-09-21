package com.example.trackingapp.ui.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "money_table")
data class Money(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name = "Income") val Income : String?,
    @ColumnInfo(name = "Expense", defaultValue = "$00.0") val Expense : String?,


)
