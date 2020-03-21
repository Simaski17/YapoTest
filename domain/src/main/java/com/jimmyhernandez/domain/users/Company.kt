package com.jimmyhernandez.domain.users

import androidx.room.ColumnInfo


data class Company (
    @ColumnInfo(name = "company_name")
    val name: String,
    val catchPhrase: String,
    val bs: String
)