package com.jimmyhernandez.yapotest.data

import com.jimmyhernandez.domain.users.UserResponse
import com.jimmyhernandez.yapotest.data.database.UserResponse as DomainUser



fun UserResponse.toRoomUser(): DomainUser =
    DomainUser(
        id,
        name,
        username,
        email,
        address,
        phone,
        website,
        company,
        favorite
    )

fun DomainUser.toDomainuser(): UserResponse =
    UserResponse(
        id,
        name,
        username,
        email,
        address,
        phone,
        website,
        company,
        favorite
    )