package com.example.simplenote.presentation.login

import androidx.lifecycle.ViewModel
import com.example.simplenote.data.User
import com.example.simplenote.utils.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class LoginViewModel: ViewModel() {
    fun login(userName: String) {
        UserManager.writeData(User(userName))
    }
}