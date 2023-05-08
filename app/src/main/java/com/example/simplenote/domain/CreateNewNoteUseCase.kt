package com.example.simplenote.domain

import com.example.simplenote.data.Note
import com.example.simplenote.domain.repo.newNote.CreateNewNoteRepository
import javax.inject.Inject

class CreateNewNoteUseCase @Inject constructor(private val createNewNoteRepository: CreateNewNoteRepository) {
    operator fun invoke(note: Note) {
        createNewNoteRepository.createNewNote(note)
    }
}