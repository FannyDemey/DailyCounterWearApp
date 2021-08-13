package com.techethic.compose.dailycounter.ui.timer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.techethic.compose.dailycounter.MainViewModel
import com.techethic.compose.dailycounter.R
import com.techethic.compose.dailycounter.tools.DateCustomFormatter
import com.techethic.compose.dailycounter.ui.statistics.Divider
import com.techethic.compose.dailycounter.ui.statistics.LastItemSpacer
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*


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
                ConstraintLayout {
                    val (time, yesterdayContractionIcon) = createRefs()
                    Text(
                        text = DateCustomFormatter.formatTimestampToTime(contraction.startedAt),
                        modifier = Modifier.constrainAs(time){
                            linkTo(start = parent.start, end = parent.end)
                            top.linkTo(parent.top)
                        },
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body2,
                    )
                    if(isContractionFromYesterday(contraction.startedAt)){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_past),
                            contentDescription = "Yesterday",
                            modifier = Modifier.size(16.dp).padding(start = 4.dp).constrainAs(yesterdayContractionIcon){
                                top.linkTo(time.top)
                                bottom.linkTo(time.bottom)
                                start.linkTo(time.end)
                            })
                    }
                }

                Divider(MaterialTheme.colors.primary, 32.dp)

                LastItemSpacer(contractions.size, index)
            }
        }
    }

}

fun isContractionFromYesterday(timestamp: Long) : Boolean {
    val stamp = Timestamp(timestamp)
    val date = Date(stamp.time)
    val fmt = SimpleDateFormat("yyyyMMdd", Locale.ENGLISH)
    Log.d("Fanny","stampDate : $date, formated : ${fmt.format(date)} now: ${fmt.format(Date.from(Instant.now()))}")
    return !fmt.format(date).equals(fmt.format(Date.from(Instant.now())))
}

@Composable
fun TimerListTitle(){
    Text("Time",
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.body2)
}
