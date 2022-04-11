package com.haydar.notesapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.haydar.notesapp.data.NotesRepository
import com.haydar.notesapp.data.local.entity.Notes
import com.haydar.notesapp.data.local.room.NotesDao
import com.haydar.notesapp.data.local.room.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {
    private val notesDao: NotesDao = NotesDatabase.getDatabase(application).notesDao()
    private val notesRepository: NotesRepository = NotesRepository(notesDao)

    val getAllNotes: LiveData<List<Notes>> = notesRepository.getAllNotes
    val sortByHighPriority: LiveData<List<Notes>> = notesRepository.sortByHighPriority
    val sortByLowPriority: LiveData<List<Notes>> = notesRepository.sortByLowPriority

    fun insertNotes(notes: Notes){
        viewModelScope.launch (Dispatchers.IO){
            notesRepository.insertNotes(notes)
        }
    }

    fun searchNotesByQuery(query: String): LiveData<List<Notes>>{
        return notesDao.searchNoteByQuery(query)
    }

    fun deleteAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteAllData()
        }
    }
    fun deleteNote(notes: Notes){
        viewModelScope.launch (Dispatchers.IO){
            notesRepository.deleteNote(notes)
        }
    }
    fun updateNote(notes: Notes){
        viewModelScope.launch (Dispatchers.IO){
            notesRepository.updateNote(notes)
        }
    }
}
