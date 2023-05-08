package com.example.simplenote.presentation.createNewNote

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplenote.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNewNoteActivity : AppCompatActivity() {
    private val viewModel: CreateNewNoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateNewNoteLayout()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun CreateNewNoteLayout() {
        var text by remember { mutableStateOf(TextFieldValue()) }
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.createNewNote(text.text)
                    },
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = "Add",
                        tint = Color.White,
                    )
                }
            },
            content = { padding ->
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text(text = stringResource(id = R.string.enter_note_here)) },
                    maxLines = 100,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                )
            }
        )
    }

    companion object {
        fun intentFor(context: Context): Intent {
            return Intent(context, CreateNewNoteActivity::class.java)
        }
    }
}