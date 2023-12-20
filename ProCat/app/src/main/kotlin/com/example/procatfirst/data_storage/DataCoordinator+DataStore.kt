package com.example.procatfirst.data_storage

import android.util.Log
import androidx.datastore.preferences.core.edit
import com.example.procatfirst.data.Tool
import com.example.procatfirst.data_storage.DataCoordinator.Companion.identifier
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
        "user email old: $userEmailPreferenceVariable"
    )
    context.dataStore.edit { preferences ->
        preferences[PreferencesKeys.userEmail] = value
        Log.i(
            identifier,
            "user email new: $userEmailPreferenceVariable"
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

suspend fun DataCoordinator.getToolsInCartDataStore(): List<Tool> {
    val context = this.context ?: return defaultToolsInCartPreferenceVariable
    val rowTools = context.dataStore.data.firstOrNull()?.get(PreferencesKeys.toolsInCart)
        ?: defaultToolsInCartPreferenceVariable
    val tools: MutableList<String> = emptyList<String>().toMutableList()
    for (i : Any in rowTools) {
        tools.add(i.toString())
    }
    val readyTools: MutableList<Tool> = emptyList<Tool>().toMutableList()
    for (i: Int in 0..<tools.size step 5) {
        val tool = Tool(tools[i].toInt(),
            tools[i+1], tools[i+2].toInt(), tools[i+3], tools[i+4]
        )
        readyTools.add(tool)
    }
    return readyTools
}

suspend fun DataCoordinator.addToolInCartDataStore(value: Tool) {
    val context = this.context ?: return
    Log.i(
        identifier,
        "tools old: $toolsInCartPreferenceVariable"
    )
    val list = setOf<String>().toMutableSet()
    list.add(value.id.toString())
    list.add(value.name)
    list.add(value.imageResId.toString())
    list.add(value.description)
    list.add(value.specifications)
    context.dataStore.edit { preferences ->
        preferences[PreferencesKeys.toolsInCart] = list
        Log.i(
            identifier,
            "tools new: $toolsInCartPreferenceVariable"
        )
    }
}

}