package com.techethic.compose.dailycounter.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Counter::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun counterDao(): CounterDao
}