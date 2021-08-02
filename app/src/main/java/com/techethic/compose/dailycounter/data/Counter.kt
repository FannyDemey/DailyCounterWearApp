package com.techethic.compose.dailycounter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Counter(
    @PrimaryKey(autoGenerate = true) val id: Int = -1,
    val count: Int,
    val date: String
)