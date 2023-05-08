package com.example.simplenote.presentation.splash

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import com.example.simplenote.presentation.login.LoginActivity
import com.example.simplenote.presentation.noteList.NoteListActivity
import com.example.simplenote.utils.UserManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {}
        }
        checkUser()
    }

    private fun checkUser() {
        UserManager.readData()
        if (UserManager.isLogin) {
            startActivity(NoteListActivity.intentFor(this))
        } else {
            startActivity(LoginActivity.intentFor(this))
        }
    }
}