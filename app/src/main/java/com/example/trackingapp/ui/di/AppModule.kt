package com.example.trackingapp.ui.di

import android.content.Context
import androidx.room.Room
import com.example.trackingapp.ui.data.TransactionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideNoteDatabase(@ApplicationContext context: Context): TransactionDatabase {
        return Room.databaseBuilder(context, TransactionDatabase::class.java, "all_transactions")
            .build()
    }




}