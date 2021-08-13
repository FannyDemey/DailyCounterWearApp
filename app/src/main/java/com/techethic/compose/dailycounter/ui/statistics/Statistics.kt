package com.techethic.compose.dailycounter.ui.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.techethic.compose.dailycounter.MainViewModel
import com.techethic.compose.dailycounter.data.Counter
import com.techethic.compose.dailycounter.tools.DateCustomFormatter.formatDateFromDbToDisplayableDate


@Composable
fun Statistics(mainViewModel : MainViewModel){
    val counters by mainViewModel.retrieveAllCounter().collectAsState(listOf())
    Column(modifier = Modifier
        .padding(horizontal = 24.dp)) {
        Text(text = "Statistics",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.title1)
        LazyColumn {
            itemsIndexed(counters?.sortedByDescending { it.date } as List<Counter>){ index ,counter ->
                if(index == 0){
                    StatisticsColumnTitle()
                    Divider(MaterialTheme.colors.primary)

                }
                Row(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = formatDateFromDbToDisplayableDate(counter.date),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body2
                    )
                    Text(
                        text = "${counter.count}",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body2
                    )
                }
                Divider(MaterialTheme.colors.secondary)
                LastItemSpacer(counters?.size, index)
            }
        }
    }

}

@Composable
fun StatisticsColumnTitle(){
    Row(modifier = Modifier.fillMaxSize()) {
        Text("Date",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body2)
        Text("Count",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body2)
    }
}

@Composable
fun Divider(color: Color){
    // or replace it with a custom one
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(color = color)
    )

}

@Composable
fun LastItemSpacer(counterSize: Int ?, currentIndex: Int){
    if(counterSize != null && (counterSize - 1 == currentIndex)){
        Spacer(modifier = Modifier.height(24.dp))
    }
}