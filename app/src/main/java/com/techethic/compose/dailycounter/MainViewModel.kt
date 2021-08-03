package com.techethic.compose.dailycounter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techethic.compose.dailycounter.data.Counter
import com.techethic.compose.dailycounter.data.CounterDao
import com.techethic.compose.dailycounter.tools.DateCustomFormatter.formatDateForQuery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

class MainViewModel(private val counterDao: CounterDao) : ViewModel() {

    private val _currentCounter : MutableStateFlow<Counter?> = MutableStateFlow(null)
    val currentCounter : StateFlow<Counter?> = _currentCounter
    fun retrieveCurrentCounter(){
        viewModelScope.launch {
            val nowDate = formatDateForQuery(Date.from(Instant.now()))
            counterDao.findCounterAtDate(nowDate).collect { counterInDb ->
                if(counterInDb == null){
                    counterDao.insert(Counter(count = 0, date = nowDate))
                    counterDao.findCounterAtDate(nowDate).collect {
                        if(it == null){
                          throw RuntimeException("counter is still null after insertion. This should not happened")
                        }
                        _currentCounter.emit(it)
                    }
                } else {
                    _currentCounter.emit(counterInDb)
                }
            }
        }

    }

    fun retrieveAllCounter() = counterDao.getAll()

    fun updateCounter(counter: Counter){
        viewModelScope.launch {
            counterDao.update(counter)
        }
    }
}