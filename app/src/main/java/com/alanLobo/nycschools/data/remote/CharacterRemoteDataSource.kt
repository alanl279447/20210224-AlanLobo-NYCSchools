package com.example.nycschools.data.remote

import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val schoolService: SchoolService
): BaseDataSource() {

    suspend fun getSchools() = getResult { schoolService.getSchoolList() }
    suspend fun getSchoolDetails(id: String) = getResult { schoolService.getSchoolDetails(id) }
}