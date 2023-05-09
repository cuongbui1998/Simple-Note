package com.example.simplenote.presentation.createNewNote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplenote.data.NetworkResponse
import com.example.simplenote.data.Note
import com.example.simplenote.domain.CreateNewNoteUseCase
import com.example.simplenote.utils.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNewNoteViewModel @Inject constructor(private val createNewNoteUseCase: CreateNewNoteUseCase) :
    ViewModel() {
    private val _createNoteSuccess = MutableLiveData<Boolean>()
    val createNoteSuccess: LiveData<Boolean> = _createNoteSuccess
    fun createNewNote(content: String) {
        viewModelScope.launch {
            createNewNoteUseCase(
                Note(
                    content = content,
                    timestamp = System.currentTimeMillis(),
                    author = UserManager.userName
                )
            ).flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is NetworkResponse.Success -> _createNoteSuccess.postValue(true)
                        is NetworkResponse.Error -> {
                            _createNoteSuccess.postValue(false)
                        }
                    }
                }
        }
    }
}