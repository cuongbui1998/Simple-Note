package com.example.simplenote.di

import com.example.simplenote.domain.repo.newNote.CreateNewNoteRepository
import com.example.simplenote.domain.repo.newNote.CreateNewNoteRepositoryImpl
import com.example.simplenote.domain.repo.noteList.NoteListRepository
import com.example.simplenote.domain.repo.noteList.NoteListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideCreateNewNoteRepository(createNewNoteRepositoryImpl: CreateNewNoteRepositoryImpl): CreateNewNoteRepository

    @Binds
    abstract fun provideNoteListRepository(noteListRepositoryImpl: NoteListRepositoryImpl): NoteListRepository
}