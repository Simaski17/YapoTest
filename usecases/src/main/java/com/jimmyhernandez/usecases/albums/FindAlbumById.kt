package com.jimmyhernandez.usecases.albums

import com.jimmyhernandez.data.repository.AlbumsRepository
import com.jimmyhernandez.domain.albums.Albums

class FindAlbumById(private val albumsRepository: AlbumsRepository) {

    suspend fun invoke(albums: Int): List<Albums> = albumsRepository.findById(albums)

}