package com.example.procattemplate.data_storage

import android.util.Log
import androidx.datastore.preferences.core.edit
import com.example.procattemplate.data_storage.DataCoordinator.Companion.identifier
import kotlinx.coroutines.flow.firstOrNull

// MARK: Sample String Functionality
// Please note that the DataStore functionality must be called within a coroutine.
suspend fun DataCoordinator.getUserEmailDataStore(): String {
    val context = this.context ?: return defaultUserEmailPreferenceValue
    return context.dataStore.data.firstOrNull()?.get(PreferencesKeys.userEmail)
        ?: defaultUserEmailPreferenceValue
}

suspend fun DataCoordinator.setUserEmailDataStore(value: String) {
    val context = this.context ?: return
    Log.i(
        identifier,
        "user email 1: $userEmailPreferenceVariable"
    )
    context.dataStore.edit { preferences ->
        preferences[PreferencesKeys.userEmail] = value
        Log.i(
            identifier,
            "user email 2: $userEmailPreferenceVariable"
        )
    }
}

suspend fun DataCoordinator.getUserPhoneDataStore(): String {
    val context = this.context ?: return defaultUserPhonePreferenceValue
    return context.dataStore.data.firstOrNull()?.get(PreferencesKeys.userPhone)
        ?: defaultUserPhonePreferenceValue
}

suspend fun DataCoordinator.setUserPhoneDataStore(value: String) {
    val context = this.context ?: return
    Log.i(
        identifier,
        "ok"
    )
    context.dataStore.edit { preferences ->
        preferences[PreferencesKeys.userPhone] = value
        Log.i(
            identifier,
            "ok"
        )
    }
}

// MARK: Sample Int Functionality
// Please note that the DataStore functionality must be called within a couroutine.
suspend fun DataCoordinator.getSampleIntDataStore(): Int {
    val context = this.context ?: return defaultSampleIntPreferenceVariable
    return context.dataStore.data.firstOrNull()?.get(PreferencesKeys.sampleInt)
        ?: defaultSampleIntPreferenceVariable
}

suspend fun DataCoordinator.setSampleIntDataStore(value: Int) {
    val context = this.context ?: return
    Log.i(
        identifier,
        "ok"
    )
    context.dataStore.edit { preferences ->
        preferences[PreferencesKeys.sampleInt] = value
        Log.i(
            identifier,
            "ok"
        )
    }
}

// MARK: Sample Boolean Functionality
// Please note that the DataStore functionality must be called within a coroutine.
suspend fun DataCoordinator.getSampleBooleanDataStore(): Boolean {
    val context = this.context ?: return defaultSampleBooleanPreferenceVariable
    return context.dataStore.data.firstOrNull()?.get(PreferencesKeys.sampleBoolean)
        ?: defaultSampleBooleanPreferenceVariable
}

suspend fun DataCoordinator.setSampleBooleanDataStore(value: Boolean) {
    val context = this.context ?: return
    Log.i(
        identifier,
        "ok"
    )
    context.dataStore.edit { preferences ->
        preferences[PreferencesKeys.sampleBoolean] = value
        Log.i(
            identifier,
            "ok"
        )
    }
}