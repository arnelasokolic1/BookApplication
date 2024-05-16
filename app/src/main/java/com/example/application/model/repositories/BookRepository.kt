package com.example.application.model.repositories

import com.example.application.model.daos.BooksDao
import com.example.application.model.models.Books
import kotlinx.coroutines.flow.Flow

class BookRepository(private val booksDao: BooksDao): BaseRepository<Books> {
    override suspend fun insert(t: Books) = booksDao.insert(t)

    override suspend fun update(t: Books) = booksDao.update(t)

    override suspend fun delete(t: Books) = booksDao.delete(t)

    override fun getOneStream(id: Int): Flow<Books?> = booksDao.getBooks(id)

    fun getBooks(): Flow<List<Books>> = booksDao.getBooks()

}

