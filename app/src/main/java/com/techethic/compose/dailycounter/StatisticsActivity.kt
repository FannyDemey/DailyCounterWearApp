package com.techethic.compose.dailycounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.techethic.compose.dailycounter.theme.MyTheme
import com.techethic.compose.dailycounter.ui.statistics.Statistics
import org.koin.android.viewmodel.ext.android.viewModel

class StatisticsActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                Statistics(mainViewModel = mainViewModel)
            }
        }
    }
    
}