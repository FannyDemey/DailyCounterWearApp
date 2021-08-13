package com.techethic.compose.dailycounter.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techethic.compose.dailycounter.data.dao.ContractionDao
import com.techethic.compose.dailycounter.data.dao.CounterDao
import com.techethic.compose.dailycounter.data.model.Contraction
import com.techethic.compose.dailycounter.data.model.Counter

@Database(entities = [Counter::class, Contraction::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun counterDao(): CounterDao
    abstract fun contractionDao(): ContractionDao

}