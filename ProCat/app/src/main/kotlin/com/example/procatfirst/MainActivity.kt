package com.example.procatfirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.procatfirst.ui.auth.AuthScreen
import com.example.procatfirst.ui.item.ToolScreen
import com.example.procatfirst.ui.start.StartScreen
import com.example.procatfirst.ui.theme.ProCatFirstTheme

import com.example.procatfirst.data_storage.DataCoordinator
import com.example.procatfirst.intents.NotificationCoordinator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBackground()
        setContent {
            ProCatFirstTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProCatApp()
                }
            }
        }
    }

    private fun initBackground() {
        NotificationCoordinator.shared.initialize(baseContext)

        DataCoordinator.shared.initialize(
            context = baseContext,
            onLoad = {
                //DataCoordinator.shared.updateUserEmail(DataCoordinator.shared.defaultUserEmailPreferenceValue)
            }
        )
    }
    
}

