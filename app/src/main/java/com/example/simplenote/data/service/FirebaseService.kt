package com.example.simplenote.data.service

import com.example.simplenote.data.Note
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseService {
    private var database: DatabaseReference = Firebase.database.reference

    fun createNewNote(note: Note) {
        database.child("notes").push().setValue(note)
    }

    fun getNoteList(listener: ValueEventListener) {
        database.child("notes").addValueEventListener(listener)
    }

    fun removeGetNoteListener(listener: ValueEventListener) {
        database.child("notes").removeEventListener(listener)
    }
}