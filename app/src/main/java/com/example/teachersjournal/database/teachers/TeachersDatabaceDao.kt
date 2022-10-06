package com.example.teachersjournal.database.teachers

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TeachersDatabaceDao {

    @Insert
     fun insert(teachers: Teachers)

    @Update
    suspend fun update(teachers: Teachers)

    @Query("SELECT * from old_teachers_database WHERE teacherId = :key")
    suspend fun get(key: Long): Teachers?

    @Query("DELETE FROM old_teachers_database")
    suspend fun clear()

    @Query("SELECT * FROM old_teachers_database ORDER BY teacherId DESC")
    fun gelAllTeacher(): LiveData<List<Teachers>>

    @Query("SELECT * FROM old_teachers_database ORDER BY teacherId DESC LIMIT 1")
    suspend fun getToTeacher(): Teachers?

}