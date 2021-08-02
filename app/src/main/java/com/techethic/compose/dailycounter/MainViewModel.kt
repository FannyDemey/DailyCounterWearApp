package com.techethic.compose.dailycounter

import androidx.lifecycle.ViewModel
import com.techethic.compose.dailycounter.data.CounterDao

class MainViewModel(private val counterDao: CounterDao) : ViewModel() {
}