package com.example.simplenote.presentation.noteList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplenote.R
import com.example.simplenote.data.Note
import com.example.simplenote.presentation.createNewNote.CreateNewNoteActivity
import com.example.simplenote.utils.UserManager
import com.example.simplenote.utils.addEmptyLines
import com.example.simplenote.utils.formatLastModifiedDate
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NoteListActivity : AppCompatActivity() {
    private val viewModel: NoteListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                NoteListLayout()
            }
        }
        UserManager.userName?.let { viewModel.getNoteList(it) }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NoteListLayout() {
        val context = LocalContext.current
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        context.startActivity(CreateNewNoteActivity.intentFor(context))
                    },
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add",
                        tint = Color.White,
                    )
                }
            },
            content = { padding ->
                val listNote by viewModel.listNote.collectAsState()
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    items(listNote) { model ->
                        ListRow(model = model)
                    }
                }
            }
        )
    }

    @Composable
    fun ListRow(model: Note?) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = model?.content?.addEmptyLines(3) ?: "".addEmptyLines(3),
                    modifier = Modifier.padding(15.dp),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                )
            }
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)
                    .fillMaxWidth()
            ) {
                model?.date?.let {
                    Text(
                        text = formatLastModifiedDate(Date(it)),
                        color = Color.Gray,
                        fontSize = 12.sp,
                    )
                }
                Spacer(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                model?.author?.let {
                    Text(
                        text = stringResource(id = R.string.written_by, it),
                        color = Color.Gray,
                        fontSize = 12.sp,
                    )
                }
            }
        }
    }

    companion object {
        fun intentFor(context: Context): Intent {
            return Intent(context, NoteListActivity::class.java)
        }
    }

}