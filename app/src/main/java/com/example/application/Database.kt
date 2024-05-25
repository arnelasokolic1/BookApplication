package com.example.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.application.model.daos.UsersDao
import com.example.application.model.daos.BooksDao
import com.example.application.model.models.Users
import com.example.application.model.models.Books


@Database(entities = [Users::class, Books::class], version = 2, exportSchema = false)
abstract class BookDatabase: RoomDatabase() {
    abstract fun userDao(): UsersDao
    abstract fun bookDao(): BooksDao

    companion object{
        @Volatile
        private var Instance: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BookDatabase::class.java, "BookAPPDatabase")
                    .fallbackToDestructiveMigration()
                    .build().also { Instance = it }
            }
        }

    }
}