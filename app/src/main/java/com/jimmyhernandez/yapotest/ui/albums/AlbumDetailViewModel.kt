package com.jimmyhernandez.yapotest.ui.albums

import androidx.lifecycle.MutableLiveData
import com.ittalent.testitandroid.ui.common.Data
import com.jimmyhernandez.domain.photos.Photos
import com.jimmyhernandez.usecases.albums.FindAlbumDetailById
import com.jimmyhernandez.usecases.albums.GetListAlbumDetailUseCase
import com.jimmyhernandez.yapotest.ui.common.ScopedViewModel
import com.jimmyhernandez.yapotest.ui.common.postException
import com.jimmyhernandez.yapotest.ui.common.postLoading
import com.jimmyhernandez.yapotest.ui.common.postValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumDetailViewModel(private val id: Int, private val findAlbumDetailById: FindAlbumDetailById, private val getListAlbumDetailUseCase: GetListAlbumDetailUseCase) : ScopedViewModel() {

    val model = MutableLiveData<Data<List<Photos>>>()

    init {
        initScope()
    }

    fun getListAlbumsDetail(){
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO){
                    getListAlbumDetailUseCase.invoke(id)
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()){
                    model.postValue(response)
                } else {
                    model.postException(Exception("${"Error"}: ${response.isEmpty().toString()}"))
                }

            }.onFailure { throwable ->
                model.postException(throwable)
            }

        }
    }

    fun findAlbumDetail(){
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO){
                   findAlbumDetailById.invoke(id)
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()){
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