package com.jimmyhernandez.usecases

import com.jimmyhernandez.data.repository.UsersRepository
import com.jimmyhernandez.domain.users.UserResponse

class GetListUsersUseCase(private val usersRepository: UsersRepository) {

    suspend fun invoke(): ArrayList<UserResponse> = usersRepository.getListUsers()

}