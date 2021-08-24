package com.techethic.compose.dailycounter.ui.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.techethic.compose.dailycounter.MainViewModel
import com.techethic.compose.dailycounter.theme.MyTheme
import org.koin.android.viewmodel.ext.android.viewModel

class TimerActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                ContractionTimerList(mainViewModel = mainViewModel)
            }
        }
    }
    
}