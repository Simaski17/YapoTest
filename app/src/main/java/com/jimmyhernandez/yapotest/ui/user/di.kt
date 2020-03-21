package com.jimmyhernandez.yapotest.ui.user

import com.jimmyhernandez.data.repository.UsersRepository
import com.jimmyhernandez.usecases.GetAllUsers
import com.jimmyhernandez.usecases.GetListUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@Module
class MainActivityModule(){
    
    @Provides
    fun usersViewModelProvider(getListUsersUseCase: GetListUsersUseCase, getAllUsers: GetAllUsers) = UsersViewModel(getListUsersUseCase, getAllUsers)

    @Provides
    fun getListUsersUseCaseProvider(usersRepository: UsersRepository) = GetListUsersUseCase(usersRepository)

    @Provides
    fun getAllUserUseCaseProvider(usersRepository: UsersRepository) = GetAllUsers(usersRepository)
    
}

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    val usersViewModel: UsersViewModel
}