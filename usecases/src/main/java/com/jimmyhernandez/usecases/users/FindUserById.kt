package com.jimmyhernandez.usecases.users

import com.jimmyhernandez.data.repository.UsersRepository
import com.jimmyhernandez.domain.users.UserResponse

class FindUserById (private val usersRepository: UsersRepository) {
    suspend fun invoke(id: Int): UserResponse = usersRepository.findById(id)
}