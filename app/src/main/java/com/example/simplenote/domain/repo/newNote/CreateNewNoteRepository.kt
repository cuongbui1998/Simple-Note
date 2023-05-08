package com.example.simplenote.domain.repo.newNote

import com.example.simplenote.data.Note

interface CreateNewNoteRepository {
    fun createNewNote(note: Note)
}