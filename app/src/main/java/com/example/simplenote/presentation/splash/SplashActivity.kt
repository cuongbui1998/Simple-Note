package com.example.simplenote.presentation.splash

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import com.example.simplenote.presentation.login.LoginActivity
import com.example.simplenote.presentation.noteList.NoteListActivity
import com.example.simplenote.utils.UserManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {}
        }
        Firebase.database.setPersistenceEnabled(true)
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