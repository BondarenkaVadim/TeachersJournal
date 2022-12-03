package com.example.teachersjournal.database.teachers

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TeachersDatabaceDao {

    @Insert
    fun insert(teachers: Teachers)

    @Update
    suspend fun update(teachers: Teachers)

    @Delete
    suspend fun deleteTeacher(teachers: Teachers)

    @Query("SELECT * from old_teachers_database WHERE teacherId = :key")
    suspend fun get(key: Long): Teachers?

    @Query("DELETE FROM old_teachers_database")
    suspend fun clear()

    @Query("SELECT * FROM old_teachers_database ORDER BY teacherId DESC")
    fun gelAllTeacher(): List<Teachers>

    @Query("SELECT * FROM old_teachers_database WHERE teacherId=:note_id")
    suspend fun getTeacher(note_id: Int): List<Teachers>

    @Query("SELECT * FROM old_teachers_database WHERE firstName LIKE :searchQuery OR secondName LIKE :searchQuery")
    fun searchDatabace(searchQuery: String): List<Teachers>
}