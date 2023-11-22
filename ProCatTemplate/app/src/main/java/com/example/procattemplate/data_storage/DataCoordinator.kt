package com.example.procattemplate.data_storage

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.preferencesDataStore
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DataCoordinator {
    companion object {
        val shared = DataCoordinator()
        const val identifier = "[DataCoordinator]"
    }
    // MARK: Variables
    var context: Context? = null
    // Create a variable for each preference, along with a default value.
    // This is to guarantee that if it can't find it it resets to a value that you can control.
    var apiRequestQueue: RequestQueue? = null
    /// Sample String
    var userPhonePreferenceVariable: String = ""
    val defaultUserPhonePreferenceValue: String = ""
    var userEmailPreferenceVariable: String = ""
    val defaultUserEmailPreferenceValue: String = ""
    /// Sample Int
    var sampleIntPreferenceVariable: Int = 0
    val defaultSampleIntPreferenceVariable: Int = 0
    /// Sample Boolean
    var sampleBooleanPreferenceVariable:  Boolean = false
    val defaultSampleBooleanPreferenceVariable: Boolean = false

    // MARK: Data Store Variables
    private val USER_PREFERENCES_NAME = "user_preferences"
    val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    // MARK: Lifecycle
    fun initialize(context: Context, onLoad: () -> Unit) {
        Log.i(
            identifier,
            "401"
        )
        // Set Context
        this.context = context
        this.apiRequestQueue = Volley.newRequestQueue(context)
        // Load DataStore Settings
        GlobalScope.launch(Dispatchers.Default) {
            // Update Sample String
            userPhonePreferenceVariable = getUserPhoneDataStore()
            userEmailPreferenceVariable = getUserEmailDataStore()
            // Update Sample Int
            sampleIntPreferenceVariable = getSampleIntDataStore()
            // Update Sample Boolean
            sampleBooleanPreferenceVariable = getSampleBooleanDataStore()
            // Log the variables to confirm that they loaded correctly
            Log.i(
                identifier,
                "init: $userEmailPreferenceVariable | $sampleIntPreferenceVariable | $sampleBooleanPreferenceVariable"

            )
            // Callback
            onLoad()
        }
    }
}