package com.example.nycschools.ui.schooldetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.nycschools.data.entities.SchoolDetailsItem
import com.example.nycschools.data.repository.SchoolRepository
import com.example.nycschools.utils.Resource

class SchoolDetailViewModel @ViewModelInject constructor(
    private val repository: SchoolRepository
) : ViewModel() {
    private val _id = MutableLiveData<String>()
    private val _schoolDetail = _id.switchMap { id ->
        repository.getSchoolDetail(id)
    }
    val schoolDetail: LiveData<Resource<SchoolDetailsItem>> = _schoolDetail
    fun start(id: String) {
        _id.value = id
    }
}