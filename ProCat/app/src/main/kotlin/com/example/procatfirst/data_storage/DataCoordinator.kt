package com.example.procatfirst.data_storage

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.procatfirst.data.Tool
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

/**
 * Класс для работы с долговременной памятью.
 * Получаем данные вот так:
 *          DataCoordinator.shared.userEmailPreferenceVariable
 * Обновляем данные в файле DataCoordinator+Update.kt
 *
 * Добавляем функционал для работы с новыми данными:
 *      Очень тяжело и во всех файликах надо правки вносить.
 *      Если сильно хочется, то можно по образцу попробовать.
 *      Можно мне написать, я умею, я сделаю.
 *      Постараюсь оптимизировать этот процесс, но думаю, что это будет не скоро.
 */
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

    var toolsInCartPreferenceVariable: MutableList<Tool> = listOf<Tool>().toMutableList()
    val defaultToolsInCartPreferenceVariable: MutableList<Tool> = listOf<Tool>().toMutableList()

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

            toolsInCartPreferenceVariable = getToolsInCartDataStore()
            // Log the variables to confirm that they loaded correctly
            Log.i(
                identifier,
                "init: $userEmailPreferenceVariable | $sampleIntPreferenceVariable | $sampleBooleanPreferenceVariable"

            )
            onLoad()
        }
    }

    private suspend fun getToolsInCartDataStore(): MutableList<Tool> {
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

    suspend fun addToolInCartDataStore(value: Tool) {
        val context = this.context ?: return
        Log.i(
            identifier,
            "tools old: $toolsInCartPreferenceVariable"
        )
        val list =  setOf<String>().toMutableSet()
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

    suspend fun removeToolInCartDataStore(value: Tool) {
        val context = this.context ?: return
        Log.i(
            identifier,
            "tools old: $toolsInCartPreferenceVariable"
        )
        val list = setOf<String>().toMutableSet()
        //list.add(value.id.toString())
        //list.add("deleted")
        //list.add(value.imageResId.toString())
        //list.add(value.description)
        //list.add(value.specifications)
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.toolsInCart] = list
            Log.i(
                identifier,
                "tools new: $toolsInCartPreferenceVariable"
            )
        }
    }

}