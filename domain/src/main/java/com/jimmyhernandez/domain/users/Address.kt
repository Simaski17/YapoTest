package com.jimmyhernandez.domain.users

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
)