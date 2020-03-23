package com.jimmyhernandez.yapotest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Albums(
    val userId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String
)