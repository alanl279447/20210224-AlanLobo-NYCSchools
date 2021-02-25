package com.example.nycschools.di

import android.content.Context
import com.example.nycschools.data.local.AppDatabase
import com.example.nycschools.data.local.SchoolDao
import com.example.nycschools.data.remote.CharacterRemoteDataSource
import com.example.nycschools.data.remote.SchoolService
import com.example.nycschools.data.repository.SchoolRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://data.cityofnewyork.us/resource/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): SchoolService = retrofit.create(SchoolService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: SchoolService) = CharacterRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.schoolDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CharacterRemoteDataSource,
                          localDataSource: SchoolDao) =
        SchoolRepository(remoteDataSource, localDataSource)
}