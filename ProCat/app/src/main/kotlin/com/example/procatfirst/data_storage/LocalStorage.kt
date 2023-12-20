package com.example.procatfirst.data_storage

import com.example.procatfirst.R
import com.example.procatfirst.api.Item
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

    fun addStuff(list: List<Item>) {
        val resultList = mutableListOf<Tool>()
        for (i: Item in list) {
            val img: Int = if(i.name == "Молоток") {
                R.drawable.hammer
            } else {
                R.drawable.set
            }
            resultList.add(Tool(i.id, i.name, img, i.description, i.specifications, i.price))
        }
        toolsStorage.addAll(resultList)
        //intent
        NotificationCoordinator.shared.sendIntent(SystemNotifications.stuffAddedIntent)
    }

    fun getStuff(): MutableList<Tool> {
        return toolsStorage
    }



}