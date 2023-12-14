package com.example.procattemplate.data_storage

import com.example.procattemplate.signals.NotificationCoordinator
import com.example.procattemplate.signals.SystemNotifications
import com.example.procattemplate.signals.sendIntent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// MARK: DataStore Update Functionality
fun DataCoordinator.updateUserEmail(value: String) {
    // Update Value
    this.userEmailPreferenceVariable = value
    // Save to System
    GlobalScope.launch(Dispatchers.Default) {
        // Update DataStore
        setUserEmailDataStore(value)
        // OPTIONAL - Send Broadcast
        NotificationCoordinator.shared.sendIntent(SystemNotifications.gotUserDataIntent, "data updated")
        // Not included in this tutorial - consult the ReadMe to learn how to setup notifications to alert your system.
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

        // Not included in this tutorial - consult the ReadMe to learn how to setup notifications to alert your system.
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