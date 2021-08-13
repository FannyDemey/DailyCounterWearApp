package com.techethic.compose.dailycounter.ui.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.techethic.compose.dailycounter.MainViewModel
import com.techethic.compose.dailycounter.tools.DateCustomFormatter
import com.techethic.compose.dailycounter.ui.statistics.Divider
import com.techethic.compose.dailycounter.ui.statistics.LastItemSpacer


@Composable
fun ContractionTimerList(mainViewModel : MainViewModel) {
    val contractions by mainViewModel.retrieveAllContractionForLast24Hours().collectAsState(listOf())
    Column(modifier = Modifier
        .padding(horizontal = 24.dp)) {
        Text(text = "Today's contractions",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.title1)
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
            itemsIndexed(contractions.sortedByDescending { it.startedAt }){ index, contraction ->
                if(index == 0){
                    TimerListTitle()
                    Divider(MaterialTheme.colors.primary, 32.dp)
                }
                Text(
                    text = DateCustomFormatter.formatTimestampToTime(contraction.startedAt),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body2,
                )
                Divider(MaterialTheme.colors.primary, 32.dp)

                LastItemSpacer(contractions.size, index)
            }
        }
    }

}
@Composable
fun TimerListTitle(){
    Text("Time",
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.body2)
}
