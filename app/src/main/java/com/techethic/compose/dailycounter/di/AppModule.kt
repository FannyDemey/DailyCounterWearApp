package com.techethic.compose.dailycounter.di

import androidx.room.Room
import com.techethic.compose.dailycounter.Application
import com.techethic.compose.dailycounter.MainViewModel
import com.techethic.compose.dailycounter.data.AppDatabase
import com.techethic.compose.dailycounter.data.CounterDao
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    //DB
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCounterDao(database: AppDatabase): CounterDao {
        return  database.counterDao()
    }

    single { provideDatabase(androidApplication() as Application) }
    single { provideCounterDao(get()) }
    viewModel { MainViewModel(get()) }

}