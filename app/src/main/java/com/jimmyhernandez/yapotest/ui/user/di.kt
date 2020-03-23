package com.jimmyhernandez.yapotest.ui.user

import com.jimmyhernandez.data.repository.UsersRepository
import com.jimmyhernandez.usecases.users.GetAllUsersUseCase
import com.jimmyhernandez.usecases.users.GetCountUsersUseCase
import com.jimmyhernandez.usecases.users.GetListFavoriteUserUseCase
import com.jimmyhernandez.usecases.users.GetListUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@Module
class UsersActivityModule(){

    @Provides
    fun usersViewModelProvider(getListUsersUseCase: GetListUsersUseCase, getAllUsersUseCase: GetAllUsersUseCase, getCountUsersUseCase: GetCountUsersUseCase, getListFavoriteUserUseCase: GetListFavoriteUserUseCase): UsersViewModel {
        return UsersViewModel(getListUsersUseCase, getAllUsersUseCase, getCountUsersUseCase, getListFavoriteUserUseCase)
    }

    @Provides
    fun getListUsersUseCaseProvider(usersRepository: UsersRepository) =
        GetListUsersUseCase(usersRepository)

    @Provides
    fun getAllUserUseCaseProvider(usersRepository: UsersRepository) =
        GetAllUsersUseCase(usersRepository)

    @Provides
    fun getCountUseCaseProvider(usersRepository: UsersRepository) =
        GetCountUsersUseCase(usersRepository)

    @Provides
    fun getListFavoriteUserUseCaseProvider(usersRepository: UsersRepository) =
        GetListFavoriteUserUseCase(usersRepository)

}

@Subcomponent(modules = [UsersActivityModule::class])
interface UsersActivityComponent {
    val usersViewModel: UsersViewModel
}