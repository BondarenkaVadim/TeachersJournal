package com.example.teachersjournal.database.teachers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "old_teachers_database")
data class Teachers(
    @PrimaryKey(autoGenerate = true)
    var teacherId: Long = 0L,

    @ColumnInfo(name = "firstName")
    var firstName: String?,

)
    /*@ColumnInfo(name = "start_time_milli")
    var startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = startTimeMilli,

    @ColumnInfo(name = "quality_rating")
    var sleepQuality: Int = -1*/
/*
    @ColumnInfo(name = "second_name")
    var secondName: String = "",


    @ColumnInfo(name = "id_subjetc")
    var id_subject: Long = 0L,

    @ColumnInfo(name = "id_class")
    var idClass: Long = 0L,

    @ColumnInfo(name = "quality_rating")
    var qualityRating: Int = -1
*/