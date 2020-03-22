package com.jimmyhernandez.yapotest.ui.user

import com.jimmyhernandez.data.repository.UsersRepository
import com.jimmyhernandez.usecases.GetAllUsersUseCase
import com.jimmyhernandez.usecases.GetCountUseCase
import com.jimmyhernandez.usecases.GetListUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@Module
class UsersActivityModule(){

    @Provides
    fun usersViewModelProvider(getListUsersUseCase: GetListUsersUseCase, getAllUsersUseCase: GetAllUsersUseCase, getCountUseCase: GetCountUseCase): UsersViewModel {
        return UsersViewModel(getListUsersUseCase, getAllUsersUseCase, getCountUseCase)
    }

    @Provides
    fun getListUsersUseCaseProvider(usersRepository: UsersRepository) = GetListUsersUseCase(usersRepository)

    @Provides
    fun getAllUserUseCaseProvider(usersRepository: UsersRepository) = GetAllUsersUseCase(usersRepository)

    @Provides
    fun getCountUseCaseProvider(usersRepository: UsersRepository) = GetCountUseCase(usersRepository)

}

@Subcomponent(modules = [UsersActivityModule::class])
interface UsersActivityComponent {
    val usersViewModel: UsersViewModel
}