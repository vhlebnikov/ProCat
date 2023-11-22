package com.example.procattemplate.signals

import android.content.Intent

fun NotificationCoordinator.sendSampleIntent(extra: String) {
    // Curate Notification
    val intent = Intent(SystemNotifications.myIntent)
    // Add data (Extras)
    intent.putExtra(
        SystemNotificationsExtras.myExtra,
        extra
    )
    // Set the package
    intent.setPackage("com.example.procattemplate")
    // Send Notification - This is found in the declaration file
    sendNotification(intent)
}