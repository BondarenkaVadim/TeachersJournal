package com.example.teachersjournal.teacher

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teachersjournal.database.teachers.TeachersDatabaceDao

class TeacherViewModelFactory(
    private val dataSource: TeachersDatabaceDao,
    private val application: Application,
    ): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeacherViewModel::class.java)){
            return TeacherViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}