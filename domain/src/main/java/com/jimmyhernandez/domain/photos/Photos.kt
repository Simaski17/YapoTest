package com.jimmyhernandez.domain.photos

data class Photos (
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)