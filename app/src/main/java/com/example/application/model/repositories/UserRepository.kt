package com.example.application.model.repositories

import com.example.application.model.daos.UsersDao
import com.example.application.model.models.Books
import com.example.application.model.models.Users
import kotlinx.coroutines.flow.Flow

class UserRepository(private val usersDao: UsersDao): BaseRepository<Users> {

    override suspend fun insert(t: Users) = usersDao.insert(t)

    override suspend fun update(t: Users) = usersDao.update(t)

    override suspend fun delete(t: Users) = usersDao.delete(t)

    override fun getOneStream(id: Int): Flow<Users?> = usersDao.getUser(id)

    fun getEmailUser(email: String): Flow<Users> = usersDao.getEmailUser(email)

    fun login(password: String, email: String): Flow<Users?> = usersDao.login(password, email)

    fun getUsers(): Flow<List<Users>> = usersDao.getUsers()

    fun getUser(id: Int): Flow<Users?> = usersDao.getUser(id)
}