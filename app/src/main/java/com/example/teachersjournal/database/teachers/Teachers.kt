package com.example.teachersjournal.database.teachers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "old_teachers_database")
data class Teachers(

    @PrimaryKey(autoGenerate = true)
    var teacherId: Int = 0,

    @ColumnInfo(name = "firstName")
    var firstName: String?,

    @ColumnInfo(name = "secondName")
    var secondName: String?,

)

