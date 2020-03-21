package com.jimmyhernandez.yapotest.ui.user

import com.jimmyhernandez.data.repository.UsersRepository
import com.jimmyhernandez.usecases.GetListUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@Module
class MainActivityModule(){
    
    @Provides
    fun usersViewModelProvider(getListUsersUseCase: GetListUsersUseCase) = UsersViewModel(getListUsersUseCase)

    @Provides
    fun getListUsersUseCaseProvide(usersRepository: UsersRepository) = GetListUsersUseCase(usersRepository)
    
}

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    val usersViewModel: UsersViewModel
}