package com.jimmyhernandez.usecases.albums

import com.jimmyhernandez.data.repository.AlbumsRepository
import com.jimmyhernandez.domain.albums.Albums

class GetListAlbumsUseCase(private val albumsRepository: AlbumsRepository) {

    suspend fun invoke(albums: Int): List<Albums> = albumsRepository.getListAlbums(albums)

}