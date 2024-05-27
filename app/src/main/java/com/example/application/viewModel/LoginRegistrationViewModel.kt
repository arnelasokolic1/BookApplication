package com.example.application.viewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.application.model.repositories.UserRepository
import kotlinx.coroutines.flow.first

class LoginRegistrationViewModel(private val userRepository: UserRepository): ViewModel() {
    /**
     * Holds current item ui state BACKEND
     */
    var usersUiState by mutableStateOf(UsersUiState())
        private set

    /* Funkcije koje su suspend mozemo pozvati u drugim klasama i tu se koristi
    coroutineScope.launch { }
    Pozivamo u RegisterScreen npr.
 */
    suspend fun register(): Boolean{
                if(validateInput()){
                    userRepository.insert(usersUiState.usersDetails.toUsers())
                    login()
            return true
        }else return false
    }

    private suspend fun validateInput(uiState: UsersDetails = usersUiState.usersDetails): Boolean {
        return with(uiState) {
            checkEmail()
        }
    }

    private suspend fun checkEmail(): Boolean{
        return userRepository.getEmailUser(usersUiState.usersDetails.email).first()
            ?.toUserUiState()?.usersDetails?.email?.isEmpty() ?: true
    }

    suspend fun login(): Boolean {
        val res = userRepository.login(usersUiState.usersDetails.password, usersUiState.usersDetails.email)
            .first()
            ?.toUserUiState(true)

        if(res != null){
            usersUiState = res
            return true
        }else{
            return false
        }
    }

    fun updateUiState(userDetails: UsersDetails) {
        usersUiState =
            UsersUiState(usersDetails = userDetails, isEntryValid = false)
    }
}