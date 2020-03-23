package com.jimmyhernandez.usecases.users

import com.jimmyhernandez.data.repository.UsersRepository

class GetCountUsersUseCase(private val usersRepository: UsersRepository) {

    suspend fun invoke(): Boolean = usersRepository.getCount()

}