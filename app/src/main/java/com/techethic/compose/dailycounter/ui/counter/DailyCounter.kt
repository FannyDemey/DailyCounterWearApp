package com.techethic.compose.dailycounter.ui.counter

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.techethic.compose.dailycounter.MainViewModel
import com.techethic.compose.dailycounter.ui.CounterOptions


@Composable
fun DailyCounter(mainViewModel: MainViewModel) {
    val count by mainViewModel.currentCounter.collectAsState()
    Log.d("Fanny","recomposing with counter $count")

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                count?.let {
                    it.count++
                    mainViewModel.updateCounter(it)
                }
            },
            modifier = Modifier
                .padding(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 8.dp)
                .size(120.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text(
                text = "${count?.count ?: "..."}",
                style = MaterialTheme.typography.body1
            )
        }
        CounterOptions(count, mainViewModel)

    }
}