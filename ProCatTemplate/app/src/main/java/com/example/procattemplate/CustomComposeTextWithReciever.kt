package com.example.procattemplate

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import com.example.procattemplate.data_storage.DataCoordinator
import com.example.procattemplate.intents.SystemNotifications

@Composable
fun CustomComposeTextWithReceiver(){

    var text by remember { mutableStateOf(DataCoordinator.shared.userEmailPreferenceVariable) }

    // MARK: Visual
    Text(text = text, fontSize = 24.sp)

    // MARK: Receivers

    val receiver: IntentsReceiverAbstractObject = object : IntentsReceiverAbstractObject() {
        override fun howToReactOnIntent() {
            text = "Wow it works!!!"
        }
    }

    receiver.CreateReceiver(intentToReact = SystemNotifications.myTestIntent)

    val receiver1: IntentsReceiverAbstractObject = object : IntentsReceiverAbstractObject() {
        override fun howToReactOnIntent() {
            text = DataCoordinator.shared.userEmailPreferenceVariable
        }
    }

    receiver1.CreateReceiver(intentToReact = SystemNotifications.gotUserDataIntent)

}