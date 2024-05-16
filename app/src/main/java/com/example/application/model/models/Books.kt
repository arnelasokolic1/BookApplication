package com.example.application.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import org.jetbrains.annotations.NotNull

@Entity(tableName = "Books")
data class Books(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "author")
    val surname: String,

    @ColumnInfo(name = "description")
    val dob: String? = null,


    )


