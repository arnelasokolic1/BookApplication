package com.example.application.viewModel


import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.application.model.models.Users

data class UsersDetails(
    val id: Int = 0,
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",

)

data class UsersUiState(
    val usersDetails: UsersDetails = UsersDetails(),
    val isEntryValid: Boolean = false
)

fun UsersDetails.toUsers(): Users = Users(
    id = id,
    name = name,
    surname = surname,
    password = password,
    email = email
)

fun Users.toUsersDetails() = UsersDetails(
    id = id,
    name = name,
    surname = surname,
    password = password,
    email = email
)

fun Users.toUserUiState(isEntryValid: Boolean = false): UsersUiState = UsersUiState(
    usersDetails = this.toUsersDetails(),
    isEntryValid = isEntryValid
)