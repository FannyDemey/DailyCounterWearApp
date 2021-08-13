package com.techethic.compose.dailycounter.data.dao

import androidx.room.*
import com.techethic.compose.dailycounter.data.model.Contraction
import com.techethic.compose.dailycounter.data.model.Counter
import kotlinx.coroutines.flow.Flow

@Dao
interface ContractionDao {
    @Query("SELECT * FROM contraction")
    fun getAll(): Flow<List<Contraction>?>

    @Query("SELECT * FROM contraction WHERE date == :date")
    fun findAllContractionsAtDate(date: String): Flow<List<Contraction>>

    @Insert
    suspend fun insert(vararg contraction: Contraction)


    @Update
    suspend fun update(vararg contraction: Contraction)

}