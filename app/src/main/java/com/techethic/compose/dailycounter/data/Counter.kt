package com.techethic.compose.dailycounter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Counter(
    @PrimaryKey(autoGenerate = true) val id: Int = -1,
    var count: Int,
    val date: String
)