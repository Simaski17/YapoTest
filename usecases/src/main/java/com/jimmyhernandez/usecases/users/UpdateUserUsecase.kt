package com.jimmyhernandez.usecases.users

import com.jimmyhernandez.data.repository.UsersRepository
import com.jimmyhernandez.domain.users.UserResponse

class UpdateUserUsecase(private val usersRepository: UsersRepository) {
    suspend fun invoke(userResponse: UserResponse): UserResponse = with(userResponse) {
        copy(favorite = !favorite).also { usersRepository.updateUser(it) }
    }
}