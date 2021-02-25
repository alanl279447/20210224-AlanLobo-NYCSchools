package com.example.nycschools.ui.schools

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.data.repository.SchoolRepository
import kotlinx.coroutines.launch

class SchoolsViewModel @ViewModelInject constructor(
    private val repository: SchoolRepository
) : ViewModel() {

    val schools  = repository.getSchools()
}