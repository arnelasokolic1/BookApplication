package com.example.application.model



import android.content.Context
import com.example.application.BookDatabase
import com.example.application.model.repositories.UserRepository
import com.example.application.model.repositories.BookRepository

interface AppContainer {
    val userRepository: UserRepository
    val bookRepository: BookRepository
}
class AppDataContainer(private val context: Context): AppContainer{

    override val userRepository: UserRepository by lazy {
        UserRepository(BookDatabase.getDatabase(context).userDao())
    }

    override val bookRepository: BookRepository by lazy {
        BookRepository(BookDatabase.getDatabase(context).bookDao())
    }


}