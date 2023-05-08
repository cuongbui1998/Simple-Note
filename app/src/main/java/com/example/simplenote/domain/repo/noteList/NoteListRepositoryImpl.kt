package com.example.simplenote.domain.repo.noteList

import com.example.simplenote.data.NetworkResponse
import com.example.simplenote.data.Note
import com.example.simplenote.data.service.FirebaseService
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NoteListRepositoryImpl @Inject constructor(private val remoteService: FirebaseService) :
    NoteListRepository {

    override fun getNoteList(author: String): Flow<NetworkResponse<List<Note?>>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listNote: MutableList<Note?> = mutableListOf()
                for (postSnapshot in dataSnapshot.children) {
                    val note = postSnapshot.getValue(Note::class.java)
                    listNote.add(note)
                }
                val result = listNote.apply {
                    sortByDescending { it?.date }
                }.filter { it?.author == author }
                trySendBlocking(NetworkResponse.Success(result))
            }

            override fun onCancelled(databaseError: DatabaseError) {
                trySendBlocking(NetworkResponse.Error(Throwable("Cancel")))
            }
        }
        remoteService.getNoteList(listener)
        awaitClose {
            remoteService.removeGetNoteListener(listener)
        }
    }
}