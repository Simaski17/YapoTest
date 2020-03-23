package com.jimmyhernandez.yapotest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserResponse>)

    @Query("SELECT COUNT(id) FROM UserResponse")
    fun usersCount(): Int

    @Query("SELECT * FROM UserResponse")
    fun getAllUsers(): List<UserResponse>

    @Query("SELECT * FROM userresponse WHERE id = :id")
    fun findById(id: Int): UserResponse

    //Albums
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbums(albums: List<Albums>)

    @Query("SELECT * FROM albums WHERE userId = :userId")
    fun findAlbumsById(userId: Int): List<Albums>

    //AlbumsDetails
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbumsDetails(albums: List<Photos>)

    @Query("SELECT * FROM photos WHERE albumId = :albumId")
    fun findAlbumDetailById(albumId: Int): List<Photos>


}