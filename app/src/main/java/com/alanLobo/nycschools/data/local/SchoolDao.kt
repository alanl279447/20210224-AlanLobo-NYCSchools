package com.example.nycschools.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nycschools.data.entities.SchoolDetailsItem
import com.example.nycschools.data.entities.SchoolListItem

@Dao
interface SchoolDao {

    @Query("SELECT * FROM schoolListItem")
    fun getAllSchools() : LiveData<List<SchoolListItem>>

    @Query("SELECT * FROM schoolListItem WHERE dbn= :id")
    fun getSchoolItem(id: String) : LiveData<SchoolListItem>

    @Query("SELECT * FROM schoolDetails WHERE dbn = :id")
    fun getSchoolDetail(id: String): LiveData<SchoolDetailsItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(schools: List<SchoolListItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(schoolDetail: SchoolDetailsItem)


}