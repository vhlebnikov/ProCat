package com.example.procattemplate.data_storage

import com.example.procatfirst.data_storage.DataCoordinator
import com.example.procatfirst.intents.NotificationCoordinator
import com.example.procatfirst.intents.SystemNotifications
import com.example.procatfirst.intents.SystemNotificationsExtras
import com.example.procatfirst.intents.sendIntent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// MARK: DataStore Update Functionality
/**
 * Методы для обновления данных  долговременной памяти.
 */
fun DataCoordinator.updateUserEmail(value: String) {
    // Update Value
    this.userEmailPreferenceVariable = value
    // Save to System
    GlobalScope.launch(Dispatchers.Default) {
        // Update DataStore
        setUserEmailDataStore(value)
        // OPTIONAL - Send Broadcast
        NotificationCoordinator.shared.sendIntent(SystemNotifications.gotUserDataIntent, SystemNotificationsExtras.myExtra, "data updated")
    }
}

// MARK: DataStore Update Functionality
fun DataCoordinator.updateUserPhone(value: String) {
    // Update Value
    this.userPhonePreferenceVariable = value
    // Save to System
    GlobalScope.launch(Dispatchers.Default) {
        // Update DataStore
        setUserPhoneDataStore(value)
        // OPTIONAL - Send Broadcast

        }
}

fun DataCoordinator.updateSampleInt(value: Int) {
    // Update Value
    this.sampleIntPreferenceVariable = value
    // Save to System
    GlobalScope.launch(Dispatchers.Default) {
        // Update DataStore
        setSampleIntDataStore(value)
        // OPTIONAL - Send Broadcast
        // Not included in this tutorial - consult the ReadMe to learn how to setup notifications to alert your system.
    }
}

fun DataCoordinator.updateSampleBoolean(value: Boolean) {
    // Update Value
    this.sampleBooleanPreferenceVariable = value
    // Save to System
    GlobalScope.launch(Dispatchers.Default) {
        // Update DataStore
        setSampleBooleanDataStore(value)
        // OPTIONAL - Send Broadcast
        // Not included in this tutorial - consult the ReadMe to learn how to setup notifications to alert your system.
    }
}