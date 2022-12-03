package com.example.teachersjournal.database.teachers

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.inappmessaging.internal.injection.qualifiers.Analytics


@Database(entities = [Teachers::class], version = 1, exportSchema = false)

abstract class TeachersDatabase: RoomDatabase() {

    abstract fun teachersDatabaceDao(): TeachersDatabaceDao

    companion object{
        @Volatile
        private  var instance: TeachersDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TeachersDatabase::class.java,
            "teachers_database"
        ).build()

    }
}