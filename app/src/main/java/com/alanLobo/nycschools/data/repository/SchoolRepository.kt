package com.example.nycschools.data.repository

import com.example.nycschools.data.local.SchoolDao
import com.example.nycschools.data.remote.CharacterRemoteDataSource
import com.example.nycschools.utils.performGetOperation
import javax.inject.Inject

class SchoolRepository @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: SchoolDao
) {

    fun getSchoolDetail(id: String) = performGetOperation(
        databaseQuery = { localDataSource.getSchoolDetail(id) },
        networkCall = { remoteDataSource.getSchoolDetails(id) },
        saveCallResult = { if (!it.isNullOrEmpty()) localDataSource.insert(it[0])}  //handling for empty details response.
    )

    fun getSchools() = performGetOperation(
        databaseQuery = { localDataSource.getAllSchools() },
        networkCall = { remoteDataSource.getSchools() },
        saveCallResult = { localDataSource.insertAll(it) }
    )
}