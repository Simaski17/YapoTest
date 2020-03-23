package com.jimmyhernandez.yapotest.data

import com.jimmyhernandez.domain.albums.Albums
import com.jimmyhernandez.domain.photos.Photos
import com.jimmyhernandez.domain.users.UserResponse
import com.jimmyhernandez.yapotest.data.database.UserResponse as DomainUser
import com.jimmyhernandez.yapotest.data.database.Albums as DomainAlbums
import com.jimmyhernandez.yapotest.data.database.Photos as DomainPhotos


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

fun Albums.toRoomAlbums(): DomainAlbums =
    DomainAlbums(
        userId,
        id,
        title
    )

fun DomainAlbums.toDomainAlbum(): Albums =
    Albums(
        userId,
        id,
        title
    )

fun Photos.toRoomPhotos(): DomainPhotos =
    DomainPhotos(
        albumId,
        id,
        title,
        url,
        thumbnailUrl
    )

fun DomainPhotos.toDomainPhotos(): Photos =
    Photos(
        albumId,
        id,
        title,
        url,
        thumbnailUrl
    )

