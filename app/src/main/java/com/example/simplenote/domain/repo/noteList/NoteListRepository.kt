package com.example.simplenote.domain.repo.noteList

import com.example.simplenote.data.NetworkResponse
import com.example.simplenote.data.Note
import kotlinx.coroutines.flow.Flow

interface NoteListRepository {
    fun getNoteList(author: String): Flow<NetworkResponse<List<Note?>>>
    fun getAllNote(): Flow<NetworkResponse<List<Note?>>>
}