package com.jimmyhernandez.usecases.users

import com.jimmyhernandez.data.repository.UsersRepository
import com.jimmyhernandez.domain.users.UserResponse

class GetAllUsersUseCase(private val usersRepository: UsersRepository) {

    suspend fun invoke(): List<UserResponse> = usersRepository.getAllUsers()

}