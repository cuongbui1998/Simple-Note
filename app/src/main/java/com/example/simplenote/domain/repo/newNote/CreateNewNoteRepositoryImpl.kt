package com.example.simplenote.domain.repo.newNote

import com.example.simplenote.data.Note
import com.example.simplenote.data.service.FirebaseService
import javax.inject.Inject

class CreateNewNoteRepositoryImpl @Inject constructor(private val remoteService: FirebaseService) :
    CreateNewNoteRepository {
    override fun createNewNote(note: Note) {
        remoteService.createNewNote(note)
    }
}