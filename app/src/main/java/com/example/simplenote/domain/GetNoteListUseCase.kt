package com.example.simplenote.domain

import com.example.simplenote.data.NetworkResponse
import com.example.simplenote.data.Note
import com.example.simplenote.domain.repo.noteList.NoteListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteListUseCase @Inject constructor(private val noteListRepository: NoteListRepository) {
    operator fun invoke(author: String): Flow<NetworkResponse<List<Note?>>> {
        return noteListRepository.getNoteList(author)
    }
}