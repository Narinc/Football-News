package com.narinc.base.ui.home

import androidx.lifecycle.ViewModel
import com.narinc.base.data.source.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel()