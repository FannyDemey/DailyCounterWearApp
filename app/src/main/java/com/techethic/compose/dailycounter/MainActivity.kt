package com.techethic.compose.dailycounter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.techethic.compose.dailycounter.theme.MyTheme
import com.techethic.compose.dailycounter.ui.counter.DailyCounter
import kotlinx.coroutines.Dispatchers
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

        lifecycleScope.launch(Dispatchers.IO) {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                val locale = resources.configuration.locales[0]
                mainViewModel.retrieveCurrentCounter(locale)
            }
        }
        setContent {
            MyTheme {
                val counter by mainViewModel.currentCounter.collectAsState()
                DailyCounter(counter) {
                    mainViewModel.updateCounter(it)
                }
            }
        }
    }

}
