package com.example.procatfirst.data_storage

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

/**
 * Идентификаторы для данных, которые будем хранить в долговременной памяти.
 */
object PreferencesKeys {
    val userEmail = stringPreferencesKey("userEmailPreferenceKey")
    val userPhone = stringPreferencesKey("userPhonePreferenceKey")
    val sampleInt = intPreferencesKey("sampleIntPreferenceKey")
    val sampleBoolean = booleanPreferencesKey("sampleBooleanPreferenceKey")
    val toolsInCart = stringSetPreferencesKey("toolsInCart")
}