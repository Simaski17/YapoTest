package com.jimmyhernandez.usecases.albums

import com.jimmyhernandez.data.repository.AlbumsRepository
import com.jimmyhernandez.domain.photos.Photos

class GetListAlbumDetailUseCase(private val albumsRepository: AlbumsRepository) {

    suspend fun invoke(albums: Int): List<Photos> = albumsRepository.getListAlbumsDetail(albums)

}