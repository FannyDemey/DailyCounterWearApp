package com.techethic.compose.dailycounter.ui.counter

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.techethic.compose.dailycounter.R
import com.techethic.compose.dailycounter.data.model.Counter


@Composable
fun DailyCounter(counter: Counter?, updateCounter : (Counter) -> Unit) {

    Log.d("Fanny","recomposing with counter $counter")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)){
            Button(
                onClick = {
                    counter?.let {
                        it.count++
                        updateCounter.invoke(it)
                    }
                },
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .size(90.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
            ) {
                Text(
                    text = "${counter?.count ?: "..."}",
                    style = MaterialTheme.typography.body1
                )
            }
            Button(
                onClick = {
                    counter?.let {
                        it.count = 0
                        updateCounter.invoke(it)
                    }
                },
                modifier = Modifier
                    .size(32.dp)
                    .border(
                        width = 2.dp,
                        shape = CircleShape,
                        color = MaterialTheme.colors.primary
                    ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_reset),
                    contentDescription = stringResource(id = R.string.reset_counter) )
            }

        }

        CounterOptions()

    }
}