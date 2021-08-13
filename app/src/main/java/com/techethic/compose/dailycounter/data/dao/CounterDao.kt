package com.techethic.compose.dailycounter.data.dao

import androidx.room.*
import com.techethic.compose.dailycounter.data.model.Counter
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterDao {
    @Query("SELECT * FROM counter")
    fun getAll(): Flow<List<Counter>?>

    @Query("SELECT * FROM counter WHERE date == :date LIMIT 1")
    fun findCounterAtDate(date: String): Flow<Counter?>

    @Insert
    suspend fun insert(vararg counter: Counter)

    @Update
    suspend fun update(vararg counter: Counter)

}