package com.example.teachersjournal.teacher

import android.app.Application
import androidx.lifecycle.*
import com.example.teachersjournal.database.teachers.Teachers
import com.example.teachersjournal.database.teachers.TeachersDatabaceDao
import com.example.teachersjournal.formatTeachers
import kotlinx.coroutines.launch


class TeacherViewModel(
    val database: TeachersDatabaceDao,
    application: Application,
) : AndroidViewModel(application) {


    private val newAddTeachers = MutableLiveData<Teachers?>()
    private val allteachers = database.gelAllTeacher()
    val list: MutableLiveData<List<String>> = MutableLiveData()


    var teacherString = Transformations.map(allteachers) { allteacthers ->
        formatTeachers(allteacthers, application.resources)
    }




    fun showAllTeachers() {
    //   teacherString.value = null
    }



    private suspend fun insert(teachers: Teachers) {
        database.insert(teachers)
    }

    private suspend fun update(teachers: Teachers) {
        database.update(teachers)
    }

    private suspend fun clear() {
        database.clear()
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackbarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent


    private val _navigateToSleepQuality = MutableLiveData<Teachers>()
    val navigateToSleepQuality: LiveData<Teachers>
        get() = _navigateToSleepQuality

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }


    fun addTeacher() {
        viewModelScope.launch {
            val newTeacher = Teachers(firstName = "vadim")
            insert(newTeacher)
        }
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }


}