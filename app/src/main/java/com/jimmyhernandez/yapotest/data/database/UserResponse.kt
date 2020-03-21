package com.jimmyhernandez.yapotest.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jimmyhernandez.domain.users.Address
import com.jimmyhernandez.domain.users.Company

@Entity
data class UserResponse (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    @Embedded
    val address: Address,
    val phone: String,
    val website: String,
    @Embedded
    val company: Company,
    val favorite: Boolean
)
