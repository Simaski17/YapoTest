package com.jimmyhernandez.yapotest.ui.detail


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ittalent.testitandroid.ui.common.Data
import com.jimmyhernandez.domain.albums.Albums
import com.jimmyhernandez.domain.users.UserResponse
import com.jimmyhernandez.usecases.albums.FindAlbumById
import com.jimmyhernandez.usecases.albums.GetListAlbumsUseCase
import com.jimmyhernandez.usecases.users.FindUserById
import com.jimmyhernandez.usecases.users.UpdateUserUsecase
import com.jimmyhernandez.yapotest.ui.common.ScopedViewModel
import com.jimmyhernandez.yapotest.ui.common.postException
import com.jimmyhernandez.yapotest.ui.common.postLoading
import com.jimmyhernandez.yapotest.ui.common.postValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val id: Int, private val findUserById: FindUserById, private val findAlbumById: FindAlbumById, private val getListAlbumsUseCase: GetListAlbumsUseCase, private val updateUserUsecase: UpdateUserUsecase) : ScopedViewModel() {

    val model = MutableLiveData<Data<UserResponse>>()
    val modelListAlbums = MutableLiveData<Data<List<Albums>>>()

    init {
        initScope()
    }


    fun findUser(){
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO){
                   findUserById.invoke(id)
                }
            }.onSuccess { response ->
                if (response.id != 0){
                    model.postValue(response)
                } else {
                    model.postException(Exception("${"Error"}: "))
                }

            }.onFailure { throwable ->
                model.postException(throwable)
            }

        }
    }

    fun getListAlbums(){
        launch {
            modelListAlbums.postLoading()

            runCatching {
                withContext(Dispatchers.IO){
                    getListAlbumsUseCase.invoke(id)
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()){
                    modelListAlbums.postValue(response)
                } else {
                    modelListAlbums.postException(Exception("${"Error"}: ${response.isEmpty().toString()}"))
                }

            }.onFailure { throwable ->
                modelListAlbums.postException(throwable)
            }

        }
    }

    fun findAlbum(){
        launch {
            modelListAlbums.postLoading()

            runCatching {
                withContext(Dispatchers.IO){
                    findAlbumById.invoke(id)
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()){
                    modelListAlbums.postValue(response)
                } else {
                    modelListAlbums.postException(Exception("${"No hay Albumes con ese id en la base de datos"}: "))
                }

            }.onFailure { throwable ->
                modelListAlbums.postException(throwable)
            }

        }
    }

    fun onFavoriteClicked(userResponse: UserResponse){
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO){
                    updateUserUsecase.invoke(userResponse)
                }
            }.onSuccess { response ->
                if (response.id != 0){
                    model.postValue(response)
                } else {
                    model.postException(Exception("${"No hay Albumes con ese id en la base de datos"}: "))
                }

            }.onFailure { throwable ->
                model.postException(throwable)
            }

        }
    }


}