package com.example.procattemplate.data_storage

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Идентификаторы для данных, которые будем хранить в долговременной памяти.
 */
object PreferencesKeys {
    val userEmail = stringPreferencesKey("userEmailPreferenceKey")
    val userPhone = stringPreferencesKey("userPhonePreferenceKey")
    val sampleInt = intPreferencesKey("sampleIntPreferenceKey")
    val sampleBoolean = booleanPreferencesKey("sampleBooleanPreferenceKey")
}