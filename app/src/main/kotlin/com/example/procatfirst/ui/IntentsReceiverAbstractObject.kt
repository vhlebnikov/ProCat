package com.example.procatfirst.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import com.example.procatfirst.intents.SystemNotificationsExtras

/**
 * Абстрактный класс для работы с intents (сигналы).
 * Внутри своего Composable класса можно создать объект для отслеживания сигналов:
 *
 * val receiver: IntentsReceiverAbstractObject = object : IntentsReceiverAbstractObject() {
 *         override fun howToReactOnIntent() {
 *             TODO - тут пишем, что хотим делать при получении сигнала
 *         }
 *     }
 *
 *     receiver.CreateReceiver(intentToReact = SystemNotifications.<SIGNAL_NAME>)
 *
 *    <SIGNAL_NAME> - имя сигнала, на который реагируем.
 *    Его предварительно нужно описать по образцу в файле SystemNotifications в папке intents
 */
abstract class IntentsReceiverAbstractObject {

    @Composable
    fun CreateReceiver(intentToReact: String) {
        // MARK: Variables
        val identifier = "[Comp.WithReceiver]"
        val context = LocalContext.current

        // MARK: BROADCAST / Notifications
        DisposableEffect(context, effect = {
            // Declare Intents and Receiver
            val broadCast = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {

                    Log.i(
                        identifier,
                        "  $intent | ${intent?.extras}"
                    )

                    val action = intent?.action ?: return
                    when (action) {
                        intentToReact -> {
                            howToReactOnIntent()
                            Log.i(
                                identifier,
                                "got Intent"
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
                        IntentFilter(intentToReact),
                        Context.RECEIVER_NOT_EXPORTED
                    )
                }
            }
            // Dispose
            onDispose {
                context.unregisterReceiver(broadCast)
            }
        })
    }

    open fun howToReactOnIntent() {}

}