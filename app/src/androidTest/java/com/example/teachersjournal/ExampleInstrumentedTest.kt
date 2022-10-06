package com.example.teachersjournal

import androidx.lifecycle.Transformations
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.teachersjournal.database.teachers.Teachers
import com.example.teachersjournal.database.teachers.TeachersDatabaceDao
import com.example.teachersjournal.database.teachers.TeachersDatabase
import com.example.teachersjournal.weather.overview.WeatherFragment
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var teachDao: TeachersDatabaceDao
    private lateinit var db: TeachersDatabase

    @Before
    fun CreateDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, TeachersDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        teachDao = db.teachersDatabaceDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetTeacher() {
        val teacher = Teachers(firstName = "Vadim")
        teachDao.insert(teacher)
    }

    @Test
    @Throws(Exception::class)
    fun testUrl() {


    }
}