package com.example.simplenote.presentation.createNewNote

import androidx.lifecycle.ViewModel
import com.example.simplenote.data.Note
import com.example.simplenote.domain.CreateNewNoteUseCase
import com.example.simplenote.utils.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateNewNoteViewModel @Inject constructor(private val createNewNoteUseCase: CreateNewNoteUseCase) :
    ViewModel() {
    fun createNewNote(content: String) {
        createNewNoteUseCase(
            Note(
                content = content,
                date = System.currentTimeMillis(),
                author = UserManager.userName
            )
        )
    }
}