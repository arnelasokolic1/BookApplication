package com.example.application.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import org.jetbrains.annotations.NotNull

@Entity(tableName = "Users")
data class Users(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "surname")
    val surname: String,

    @ColumnInfo(name = "email")
    val dob: String? = null,


    @ColumnInfo(name = "password")
    val password: String,

    )


