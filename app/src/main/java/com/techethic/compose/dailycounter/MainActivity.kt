package com.techethic.compose.dailycounter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.wear.compose.material.*
import com.techethic.compose.dailycounter.data.Counter
import com.techethic.compose.dailycounter.theme.TestTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a new coroutine since repeatOnLifecycle is a suspend function
        lifecycleScope.launch {
            // The block passed to repeatOnLifecycle is executed when the lifecycle
            // is at least RESUMED and is cancelled when the lifecycle is STOPPED.
            // It automatically restarts the block when the lifecycle is STARTED again.
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                // Safely collect from locationFlow when the lifecycle is STARTED
                // and stops collection when the lifecycle is STOPPED
                mainViewModel.retrieveCurrentCounter()
            }
        }
        setContent {
            TestTheme {
                // A surface container using the 'background' color from the theme
                DailyCounter(mainViewModel)
            }
        }
    }
}


@Composable
fun DailyCounter(mainViewModel: MainViewModel) {
    val count by mainViewModel.currentCounter.collectAsState()
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

@Composable
fun CounterOptions(count: Counter?, mainViewModel : MainViewModel){
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