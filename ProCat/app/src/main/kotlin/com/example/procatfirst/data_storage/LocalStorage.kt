package com.example.procatfirst.data_storage

import com.example.procatfirst.data.Tool
import com.example.procatfirst.intents.NotificationCoordinator
import com.example.procatfirst.intents.SystemNotifications
import com.example.procatfirst.intents.sendIntent

class LocalStorage {

    private var toolsStorage: MutableList<Tool> = mutableListOf()

    companion object {
        val shared = LocalStorage()
        const val identifier = "[LocalStorage]"
    }

    fun addStuff(list: List<Tool>) {
        toolsStorage.addAll(list)
        //intent
        NotificationCoordinator.shared.sendIntent(SystemNotifications.stuffAddedIntent)
    }

    fun getStuff(): MutableList<Tool> {
        return toolsStorage
    }



}