package com.example.nycschools.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schoolDetails")
data class SchoolDetailsItem(
    @PrimaryKey
    val dbn: String,
    val num_of_sat_test_takers: String,
    val sat_critical_reading_avg_score: String = "Unknown",
    val sat_math_avg_score: String = "Unknown",
    val sat_writing_avg_score: String =  "Unknown",
    val school_name: String
)