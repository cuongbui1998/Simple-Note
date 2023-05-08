package com.example.simplenote.presentation.noteList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplenote.data.NetworkResponse
import com.example.simplenote.data.Note
import com.example.simplenote.domain.GetNoteListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(private val noteListUseCase: GetNoteListUseCase) :
    ViewModel() {
    private val _listNote = MutableStateFlow<List<Note?>>(emptyList())
    val listNote: StateFlow<List<Note?>> = _listNote
    fun getNoteList(userName: String) {
        viewModelScope.launch {
            noteListUseCase(userName)
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is NetworkResponse.Success -> _listNote.emit(it.data)
                        is NetworkResponse.Error -> {
                            Log.e("cuong", it.exception.toString())
                        }
                    }
                }

        }
    }
}