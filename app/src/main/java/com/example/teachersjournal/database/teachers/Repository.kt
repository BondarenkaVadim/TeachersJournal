package com.example.teachersjournal.database.teachers

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val personDao: TeachersDatabaceDao
) {


    fun searchDatabase(searchQuery: String): List<Teachers> {
        return personDao.searchDatabace(searchQuery)
    }

}