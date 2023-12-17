package com.example.procatfirst.intents;

import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * К этому статическому классу обращаемся для создания сигналов.
 * Метод sendIntent() описан в файле NotificationCoordinator+InternalIntents.kt
 * Методы из этого файла трогать не нужно. (не получилось сделать их приватными)
 * PS: метод initialize() конечно всё таки придётся вызвать на этапе запуска приложения.
 */
class NotificationCoordinator {
    // MARK: Variables
    companion object {
        val shared = NotificationCoordinator()
        const val identifier = "[NotificationCoordinator]"
    }
    private var context: Context? = null

    // MARK: Lifecycle
    fun initialize(context: Context) {
        Log.i(
            identifier,
            " initialize ",
        )
        this.context = context
    }

    // MARK: Send Notification Functionality
    /**
     * Please, use sendIntent() method instead of that one.
     */
    fun sendNotification(intent: Intent) {
        val context = this.context ?: return
        Log.i(
            identifier,
            "sending notification with intent $intent"
        )
        context.sendBroadcast(intent)
    }
}
