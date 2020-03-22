package com.jimmyhernandez.yapotest.ui.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ittalent.testitandroid.ui.common.Data
import com.jimmyhernandez.domain.users.UserResponse
import com.jimmyhernandez.usecases.GetAllUsersUseCase
import com.jimmyhernandez.usecases.GetCountUseCase
import com.jimmyhernandez.usecases.GetListUsersUseCase
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
    private val getCountUseCase: GetCountUseCase
) : ScopedViewModel() {


    val model = MutableLiveData<Data<ArrayList<UserResponse>>>()
    val modelGetAll = MutableLiveData<Data<List<UserResponse>>>()
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
            modelGetAll.postLoading()

            runCatching {
                withContext(Dispatchers.IO) {
                    getAllUsersUseCase.invoke()
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()) {
                    modelGetAll.postValue(response)
                } else {
                    modelGetAll.postException(Exception("${"Error"}: ${response.isEmpty().toString()}"))
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
                    getCountUseCase.invoke()
                }
            }.onSuccess { response ->
                modelCount.postValue(response)
                Log.e("RESPONSE", "RESPONSE GETCOUNT $response")
            }.onFailure {

            }
        }
    }

}