package com.example.simplenote.domain.repo.newNote

import com.example.simplenote.data.NetworkResponse
import com.example.simplenote.data.Note
import kotlinx.coroutines.flow.Flow

interface CreateNewNoteRepository {
    fun createNewNote(note: Note): Flow<NetworkResponse<Any>>
}