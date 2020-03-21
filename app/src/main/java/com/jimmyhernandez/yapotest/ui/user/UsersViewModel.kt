package com.jimmyhernandez.yapotest.ui.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ittalent.testitandroid.ui.common.Data
import com.jimmyhernandez.domain.users.UserResponse
import com.jimmyhernandez.usecases.GetListUsersUseCase
import com.jimmyhernandez.yapotest.ui.common.ScopedViewModel
import com.jimmyhernandez.yapotest.ui.common.postLoading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersViewModel(private val getListUsersUseCase: GetListUsersUseCase): ScopedViewModel() {


    val model = MutableLiveData<Data<ArrayList<UserResponse>>>()

    init {
        initScope()
    }

    fun getListUsers(){

        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO) {
                    getListUsersUseCase.invoke()
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()){
                    Log.e("RESPONSE", "RESPONSE $response")
                }
            }.onFailure {

            }
        }
    }

}