package com.jimmyhernandez.yapotest.ui.albums

import com.jimmyhernandez.data.repository.AlbumsRepository
import com.jimmyhernandez.usecases.albums.FindAlbumDetailById
import com.jimmyhernandez.usecases.albums.GetListAlbumDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class AlbumsActivityModule(private val id: Int) {

    @Provides
    fun albumDetailViewModelProvider(findAlbumDetailById: FindAlbumDetailById , getListAlbumDetailUseCase: GetListAlbumDetailUseCase): AlbumDetailViewModel {
        return AlbumDetailViewModel(id, findAlbumDetailById, getListAlbumDetailUseCase) }

    @Provides
    fun findAlbumDetailByIdProvider(albumsRepository: AlbumsRepository) = FindAlbumDetailById(albumsRepository)

    @Provides
    fun getListAlbumDetailUseCaseProvider(albumsRepository: AlbumsRepository) = GetListAlbumDetailUseCase(albumsRepository)

}

@Subcomponent(modules = [AlbumsActivityModule::class])
interface AlbumsActivityComponent {
    val albumDetailViewModel: AlbumDetailViewModel
}