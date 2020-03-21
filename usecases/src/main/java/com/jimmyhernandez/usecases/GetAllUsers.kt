package com.jimmyhernandez.usecases

import com.jimmyhernandez.data.repository.UsersRepository
import com.jimmyhernandez.domain.users.UserResponse

class GetAllUsers(private val usersRepository: UsersRepository) {

    suspend fun invoke(): List<UserResponse> = usersRepository.getAllUsers()

}