package com.techethic.compose.dailycounter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techethic.compose.dailycounter.theme.TestTheme
import com.techethic.compose.dailycounter.ui.DailyCounter
import com.techethic.compose.dailycounter.ui.statistics.Statistics
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

/*
* adb forward tcp:4444 localabstract:/adb-hub
*  adb connect 127.0.0.1:4444
* */
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                mainViewModel.retrieveCurrentCounter()
            }
        }
        setContent {
            TestTheme {
                MainApp(mainViewModel = mainViewModel)
            }
        }
    }

}


@Composable
fun MainApp(mainViewModel: MainViewModel){
    DailyCounter(mainViewModel)
}