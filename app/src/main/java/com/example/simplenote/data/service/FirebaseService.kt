package com.example.simplenote.data.service

import com.example.simplenote.data.Note
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseService {
    private var database: DatabaseReference = Firebase.database.reference

    fun createNewNote(
        note: Note,
        onSuccessListener: OnSuccessListener<Void>,
        onFailureListener: OnFailureListener
    ) {
        database.child("notes").push().setValue(note).addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }

    fun getNoteList(listener: ValueEventListener) {
        database.child("notes").addValueEventListener(listener)
    }

    fun removeGetNoteListener(listener: ValueEventListener) {
        database.child("notes").removeEventListener(listener)
    }
}