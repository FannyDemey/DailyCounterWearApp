package com.techethic.compose.dailycounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Counter(
    var count: Int,
    @PrimaryKey val date: String
)