package com.jimmyhernandez.yapotest.ui.detail

import com.jimmyhernandez.data.repository.AlbumsRepository
import com.jimmyhernandez.data.repository.UsersRepository
import com.jimmyhernandez.usecases.albums.FindAlbumById
import com.jimmyhernandez.usecases.albums.GetListAlbumsUseCase
import com.jimmyhernandez.usecases.users.FindUserById
import com.jimmyhernandez.usecases.users.UpdateUserUsecase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailActivityModule(private val id: Int) {

    @Provides
    fun detailViewModelProvider(findUserById: FindUserById, findAlbumById: FindAlbumById,getListAlbumsUseCase: GetListAlbumsUseCase, updateUserUsecase: UpdateUserUsecase): DetailViewModel {
        return DetailViewModel(id, findUserById, findAlbumById, getListAlbumsUseCase, updateUserUsecase) }

    @Provides
    fun findUserById(usersRepository: UsersRepository) = FindUserById(usersRepository)

    @Provides
    fun findAlbumsById(albumsRepository: AlbumsRepository) = FindAlbumById(albumsRepository)

    @Provides
    fun getListAlbumsUseCaseProvider(albumsRepository: AlbumsRepository) = GetListAlbumsUseCase(albumsRepository)

    @Provides
    fun updateUserUseCaseProvider(usersRepository: UsersRepository) =
        UpdateUserUsecase(usersRepository)

}

@Subcomponent(modules = [DetailActivityModule::class])
interface DetailActivityComponent {
    val detailViewModel: DetailViewModel
}