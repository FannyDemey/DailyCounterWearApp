package com.techethic.compose.dailycounter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techethic.compose.dailycounter.data.dao.ContractionDao
import com.techethic.compose.dailycounter.data.model.Counter
import com.techethic.compose.dailycounter.data.dao.CounterDao
import com.techethic.compose.dailycounter.data.model.Contraction
import com.techethic.compose.dailycounter.tools.DateCustomFormatter.formatDateForQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class MainViewModel(private val counterDao: CounterDao, private val contractionDao: ContractionDao) : ViewModel() {

    private val _currentCounter : MutableStateFlow<Counter?> = MutableStateFlow(null)
    val currentCounter : StateFlow<Counter?> = _currentCounter
    fun retrieveCurrentCounter(locale : Locale){
        viewModelScope.launch(Dispatchers.IO) {
            val nowDate = formatDateForQuery(Date.from(Instant.now()), locale)
            counterDao.findCounterAtDate(nowDate).collect { counterInDb ->
                if(counterInDb == null){
                    val newCounter = Counter(count = 0, date = nowDate)
                    counterDao.insert(newCounter)
                    _currentCounter.emit(newCounter)
                    Log.d("Fanny","emit NEW counter $newCounter")
                } else {
                    _currentCounter.emit(counterInDb)
                    Log.d("Fanny","emit counter in DB $counterInDb")

                }
            }
        }

    }

    fun retrieveAllCounter() = counterDao.getAll()

    fun retrieveAllContractionForLast24Hours() : Flow<List<Contraction>> {
        val now = Date.from(Instant.now()).time
        val nowMinus24Hours = Date.from(Instant.now().minus(24, ChronoUnit.HOURS)).time
        return contractionDao.findAllContractionsBetweenTimestamp(nowMinus24Hours, now)
    }

    fun updateCounter(counter: Counter){
        viewModelScope.launch {
            counterDao.update(counter)
            contractionDao.insert(Contraction(date = counter.date, startedAt = System.currentTimeMillis()))
        }
    }
}