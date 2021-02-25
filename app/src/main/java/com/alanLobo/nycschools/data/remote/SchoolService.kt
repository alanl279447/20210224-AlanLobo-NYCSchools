package com.example.nycschools.data.remote

import com.example.nycschools.data.entities.SchoolDetails
import com.example.nycschools.data.entities.SchoolList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolService {

    @GET("s3k6-pzi2.json")
    suspend fun getSchoolList(): Response<SchoolList>

    //TODO rather than loading 440 entries, provide an option to load based on query params!
    @GET("s3k6-pzi2.json")
    suspend fun getSchoolListByFilter(@Query("borough") borough: String): Response<SchoolList>

    @GET("f9bf-2cp4.json")
    suspend fun getSchoolDetails(@Query("dbn") dbn: String): Response<SchoolDetails>
}