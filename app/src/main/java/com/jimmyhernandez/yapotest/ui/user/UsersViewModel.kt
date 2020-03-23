package com.jimmyhernandez.yapotest.ui.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ittalent.testitandroid.ui.common.Data
import com.jimmyhernandez.domain.users.UserResponse
import com.jimmyhernandez.usecases.users.GetAllUsersUseCase
import com.jimmyhernandez.usecases.users.GetCountUsersUseCase
import com.jimmyhernandez.usecases.users.GetListFavoriteUserUseCase
import com.jimmyhernandez.usecases.users.GetListUsersUseCase
import com.jimmyhernandez.yapotest.ui.common.ScopedViewModel
import com.jimmyhernandez.yapotest.ui.common.postException
import com.jimmyhernandez.yapotest.ui.common.postLoading
import com.jimmyhernandez.yapotest.ui.common.postValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersViewModel(
    private val getListUsersUseCase: GetListUsersUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getCountUsersUseCase: GetCountUsersUseCase,
    private val getListFavoriteUserUseCase: GetListFavoriteUserUseCase
) : ScopedViewModel() {


    val model = MutableLiveData<Data<List<UserResponse>>>()
    val modelCount = MutableLiveData<Data<Boolean>>()

    init {
        initScope()
    }

    fun getListUsers() {

        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO) {
                    getListUsersUseCase.invoke()
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()) {
                    model.postValue(response)
                } else {
                    model.postException(Exception("${"Error"}: ${response.isEmpty().toString()}"))
                }
            }.onFailure {

            }
        }
    }

    fun getAllUsers() {
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO) {
                    getAllUsersUseCase.invoke()
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()) {
                    model.postValue(response)
                } else {
                    model.postException(Exception("${"Error"}: ${response.isEmpty().toString()}"))
                }
            }.onFailure {

            }
        }
    }

    fun getCount() {
        launch {
            modelCount.postLoading()

            runCatching {
                withContext(Dispatchers.IO) {
                    getCountUsersUseCase.invoke()
                }
            }.onSuccess { response ->
                modelCount.postValue(response)
                Log.e("RESPONSE", "RESPONSE GETCOUNT $response")
            }.onFailure {
                Log.e("RESPONSE", "RESPONSE MESSAGE ${it.message}")
            }
        }
    }

    fun getFavoriteUser(){
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO) {
                   getListFavoriteUserUseCase.invoke()
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()) {
                    model.postValue(response)
                } else {
                    model.postException(Exception("${"Error"}: ${response.isEmpty().toString()}"))
                }
            }.onFailure {

            }
        }
    }

}