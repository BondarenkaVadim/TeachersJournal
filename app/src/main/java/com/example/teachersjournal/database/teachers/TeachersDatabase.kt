package com.example.teachersjournal.database.teachers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Teachers::class], version = 1, exportSchema = false)
abstract class TeachersDatabase: RoomDatabase() {
    abstract val teachersDatabaceDao: TeachersDatabaceDao

    companion object{
        @Volatile
        private  var INSTANCE: TeachersDatabase? = null

        fun getInstance(context: Context): TeachersDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TeachersDatabase::class.java,
                        //"old_teachers_database"
                        "teachers_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}