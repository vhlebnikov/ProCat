package com.example.procattemplate

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.RECEIVER_NOT_EXPORTED
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.procattemplate.data_storage.DataCoordinator
import com.example.procattemplate.signals.SystemNotifications
import com.example.procattemplate.signals.SystemNotificationsExtras


@Composable
fun SampleComposableWithReceiver(name: String, modifier: Modifier) {
    // MARK: Variables
    val identifier = "[Comp.WithReceiver]"
    val context = LocalContext.current
    var text by remember {mutableStateOf(DataCoordinator.shared.userEmailPreferenceVariable)}

    // MARK: Visual
    Text(text = text, fontSize = 24.sp)

    // MARK: BROADCAST / Notifications
    DisposableEffect(context, effect = {
        // Declare Intents and Receiver
        val broadCast = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                text = DataCoordinator.shared.userEmailPreferenceVariable
                Log.i(
                    identifier,
                    "  $intent | ${intent?.extras}"
                )

                val action = intent?.action ?: return
                when (action) {
                    SystemNotifications.myIntent -> {
                        Log.i(
                            identifier,
                            " sampleIntent."
                        )
                        // Check for Extras
                        val extras = intent.extras
                        if (extras != null) {
                            // There are extras!
                            val extra = extras.getString(SystemNotificationsExtras.myExtra)

                            Log.i(
                                identifier,
                                " onSampleIntent extra: $extra."
                            )

                        } else {
                            // There are no extras
                        }
                        Log.i(
                            identifier,
                            " onSampleIntent Sequence Complete"
                        )
                    }
                }
            }
        }
        // Register Intents
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.registerReceiver(
                    broadCast,
                    IntentFilter(SystemNotifications.myIntent),
                    RECEIVER_NOT_EXPORTED
                )
            }
        }
        // Dispose
        onDispose {
            context.unregisterReceiver(broadCast)
        }
    })
}