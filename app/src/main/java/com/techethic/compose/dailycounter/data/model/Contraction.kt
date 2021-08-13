package com.techethic.compose.dailycounter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Contraction (
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val date : String,
    val startedAt: Long,
    val stoppedAt: Long? = null
)