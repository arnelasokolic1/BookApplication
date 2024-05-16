package com.example.application.model.daos


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.application.model.models.Books
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(books: Books)

    @Update
    suspend fun update(books: Books)

    @Delete
    suspend fun delete(books: Books)

    @Query("SELECT * FROM Books WHERE id = :id")
    fun getBooks(id: Int): Flow<Books>

    @Query("SELECT * FROM Books")
    fun getBooks(): Flow<List<Books>>
}