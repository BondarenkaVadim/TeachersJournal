package com.example.teachersjournal.teacher

import android.app.Application

import android.text.method.TextKeyListener.clear
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.teachersjournal.R
import com.example.teachersjournal.database.teachers.Repository
import com.example.teachersjournal.database.teachers.Teachers
import com.example.teachersjournal.database.teachers.TeachersDatabaceDao
import com.example.teachersjournal.formatTeachers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class TeacherViewModel @ViewModelInject constructor(
    private val repository: Repository,
) : ViewModel() {


    fun searchDatabase(searchQuery: String): List<Teachers> {
        return repository.searchDatabase(searchQuery)
    }
}
