package com.techethic.compose.dailycounter.ui.counter

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.techethic.compose.dailycounter.MainViewModel
import com.techethic.compose.dailycounter.R


@Composable
fun DailyCounter(mainViewModel: MainViewModel) {
    val count by mainViewModel.currentCounter.collectAsState()
    Log.d("Fanny","recomposing with counter $count")

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)){
            Button(
                onClick = {
                    count?.let {
                        it.count++
                        mainViewModel.updateCounter(it)
                    }
                },
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .size(90.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
            ) {
                Text(
                    text = "${count?.count ?: "..."}",
                    style = MaterialTheme.typography.body1
                )
            }
            Button(
                onClick = {
                    count?.let {
                        it.count = 0
                        mainViewModel.updateCounter(it)
                    }
                },
                modifier = Modifier.size(32.dp).border(width = 2.dp,
                    shape = CircleShape,
                    color = MaterialTheme.colors.primary),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_reset),
                    contentDescription = "Reset counter" )
            }

        }

        CounterOptions()

    }
}