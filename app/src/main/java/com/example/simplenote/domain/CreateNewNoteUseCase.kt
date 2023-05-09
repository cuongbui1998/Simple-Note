package com.example.simplenote.domain

import com.example.simplenote.data.NetworkResponse
import com.example.simplenote.data.Note
import com.example.simplenote.domain.repo.newNote.CreateNewNoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateNewNoteUseCase @Inject constructor(private val createNewNoteRepository: CreateNewNoteRepository) {
    operator fun invoke(note: Note): Flow<NetworkResponse<Any>> {
        return createNewNoteRepository.createNewNote(note)
    }
}