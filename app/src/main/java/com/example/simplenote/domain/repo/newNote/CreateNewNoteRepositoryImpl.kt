package com.example.simplenote.domain.repo.newNote

import com.example.simplenote.data.NetworkResponse
import com.example.simplenote.data.Note
import com.example.simplenote.data.service.FirebaseService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CreateNewNoteRepositoryImpl @Inject constructor(private val remoteService: FirebaseService) :
    CreateNewNoteRepository {
    override fun createNewNote(note: Note): Flow<NetworkResponse<Any>> = callbackFlow {
        remoteService.createNewNote(note, {
            trySendBlocking(NetworkResponse.Success(Any()))
        }, {
            trySendBlocking(NetworkResponse.Error(Throwable("Error")))
        })
        awaitClose {}
    }
}