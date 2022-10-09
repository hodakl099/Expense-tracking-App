package com.example.trackingapp.ui.utils

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.trackingapp.R

@SuppressLint("SupportAnnotationUsage")
data class TransactionCategory(
    @StringRes
    val categoryDescription : Int,
    @DrawableRes
    val categoryIcon: Int
) {


    companion object {

        val Bills = TransactionCategory(
            categoryDescription = R.string.bills,
            categoryIcon = R.drawable.ic_bill
        )

        val Food = TransactionCategory(
            categoryDescription = R.string.food,
            categoryIcon = R.drawable.ic_food
        )

        val Education = TransactionCategory(
            categoryDescription = R.string.education,
            categoryIcon = R.drawable.ic_education
        )

        val Entertainment = TransactionCategory(
            categoryDescription = R.string.entertainment,
            categoryIcon = R.drawable.ic_entertainment
        )

        val Housing = TransactionCategory(
            categoryDescription = R.string.housing,
            categoryIcon = R.drawable.ic_home
        )

        val Health = TransactionCategory(
            categoryDescription = R.string.health,
            categoryIcon = R.drawable.ic_health
        )

        val Travel = TransactionCategory(
            categoryDescription = R.string.travel,
            categoryIcon = R.drawable.ic_travel
        )

        val Transportation = TransactionCategory(
            categoryDescription = R.string.transportation,
            categoryIcon = R.drawable.ic_transportation
        )

        val Shopping = TransactionCategory(
            categoryDescription = R.string.shopping,
            categoryIcon = R.drawable.ic_cart_shop
        )

        val Salary = TransactionCategory(
            categoryDescription = R.string.salary,
            categoryIcon = R.drawable.ic_salary
        )

        val Investments = TransactionCategory(
            categoryDescription = R.string.investments,
            categoryIcon = R.drawable.ic_investment
        )

        val Other = TransactionCategory(
            categoryDescription = R.string.other,
            categoryIcon = R.drawable.ic_other
        )


        val TRANSACTION_CATEGORIES = arrayListOf(
            Bills,
            Food,
            Education,
            Entertainment,
            Housing,
            Travel,
            Transportation,
            Shopping,
            Salary,
            Investments,
            Other
        )
    }


}
