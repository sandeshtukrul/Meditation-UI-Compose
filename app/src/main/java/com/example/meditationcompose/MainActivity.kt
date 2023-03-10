package com.example.meditationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.meditationcompose.ui.HomeScreen
import com.example.meditationcompose.ui.theme.MeditationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationComposeTheme {
                HomeScreen()
            }
        }
    }
}
