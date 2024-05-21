package com.example.application.model.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.application.model.models.Books
import com.example.application.model.models.Users
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: Users)

    @Update
    suspend fun update(user: Users)

    @Delete
    suspend fun delete(user: Users)

    @Query("SELECT * FROM Users WHERE id = :id")
    fun getUser(id: Int): Flow<Users>

    @Query("SELECT * FROM Users WHERE email = :email")
    fun getEmailUser(email: String): Flow<Users>

    @Query("SELECT * FROM Users WHERE password = :password AND email = :email")
    fun login(password: String, email: String): Flow<Users?>

    @Query("SELECT * FROM Users")
    fun getUsers(): Flow<List<Users>>

}