package com.jimmyhernandez.yapotest.ui.main

import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@Module
class MainActivityModule(){

    @Provides
    fun mainViewModelProvider() = MainViewModel()
//
//    @Provides
//    fun getListUsersUseCaseProvider(usersRepository: UsersRepository) = GetListUsersUseCase(usersRepository)
//
//    @Provides
//    fun getAllUserUseCaseProvider(usersRepository: UsersRepository) = GetAllUsers(usersRepository)

}

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    val mainViewModel: MainViewModel
}