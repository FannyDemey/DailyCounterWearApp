package com.techethic.compose.dailycounter.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import com.techethic.compose.dailycounter.MainViewModel
import com.techethic.compose.dailycounter.R
import com.techethic.compose.dailycounter.data.Counter


@Composable
fun CounterOptions(count: Counter?, mainViewModel : MainViewModel, navController: NavController){
    Row {
        Button(
            onClick = {
                count?.let {
                    it.count = 0
                    mainViewModel.updateCounter(it)
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_reset),
                contentDescription = "Reset counter" )
        }
        Spacer(Modifier.size(4.dp))
        Button(
            onClick = {
                      navController.navigate("statistics")
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_show_stats),
                contentDescription = "Statistics"
            )
        }
    }

}