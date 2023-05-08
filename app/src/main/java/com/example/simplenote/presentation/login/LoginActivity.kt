package com.example.simplenote.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplenote.R
import com.example.simplenote.presentation.noteList.NoteListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginLayout()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun LoginLayout() {
        var text by remember { mutableStateOf(TextFieldValue()) }
        val context = LocalContext.current

        MaterialTheme {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text(text = stringResource(id = R.string.enter_user_name)) },
                    singleLine = true,
                    modifier = Modifier.padding(10.dp)
                )
                Button(onClick = {
                    viewModel.login(text.text)
                    context.startActivity(NoteListActivity.intentFor(context))
                }) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        }
    }

    companion object {
        fun intentFor(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}