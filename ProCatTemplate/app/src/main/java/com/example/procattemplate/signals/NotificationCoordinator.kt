package com.example.procattemplate.signals;

import android.content.Context
import android.content.Intent
import android.util.Log


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
    fun sendNotification(intent: Intent) {
        val context = this.context ?: return
        Log.i(
            identifier,
            "sending notification with intent $intent"
        )
        context.sendBroadcast(intent)
    }
}
