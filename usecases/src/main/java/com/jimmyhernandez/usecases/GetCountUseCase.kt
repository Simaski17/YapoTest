package com.jimmyhernandez.usecases

import com.jimmyhernandez.data.repository.UsersRepository

class GetCountUseCase(private val usersRepository: UsersRepository) {

    suspend fun invoke(): Boolean = usersRepository.getCount()

}