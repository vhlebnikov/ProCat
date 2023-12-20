package com.example.procatfirst.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.procatfirst.R
import com.example.procatfirst.data.Tool
import com.example.procatfirst.data_storage.DataCoordinator
import com.example.procatfirst.data_storage.updateRemoveToolsInCart
import com.example.procatfirst.intents.SystemNotifications
import com.example.procatfirst.ui.IntentsReceiverAbstractObject

@Composable
fun ToolCard() {
    var isActive by remember { mutableStateOf(DataCoordinator.shared.toolsInCartPreferenceVariable.isNotEmpty())}
    if(isActive) {
        val tools: MutableList<Tool> = DataCoordinator.shared.toolsInCartPreferenceVariable
        var toolName by remember {mutableStateOf(tools[0].name)}
        var toolImgId by remember { mutableIntStateOf(tools[0].imageResId) }
        val receiver: IntentsReceiverAbstractObject = object : IntentsReceiverAbstractObject() {
            override fun howToReactOnIntent() {
                toolName = tools[0].name
                toolImgId = tools[0].imageResId
            }
        }
        val receiver1: IntentsReceiverAbstractObject = object : IntentsReceiverAbstractObject() {
            override fun howToReactOnIntent() {
                isActive = false
            }
        }
        receiver1.CreateReceiver(intentToReact = SystemNotifications.delInCartIntent)
        receiver.CreateReceiver(intentToReact = SystemNotifications.myTestIntent)

        val cardRow = Row (Modifier.background(Color.LightGray)) {
            Image(
                painter = painterResource(id = toolImgId),
                contentDescription = stringResource(id = R.string.hammer),
                modifier = Modifier
                    .size(60.dp)
                    .aspectRatio(1.0f) // Сохраняет соотношение сторон 1:1
                    .padding(top = 2.dp, bottom = 2.dp)
            )
            Text(text = toolName, fontSize = 24.sp)
            Spacer(Modifier.size(5.dp))
            Button(onClick = { DataCoordinator.shared.updateRemoveToolsInCart(tools[0]) }) {
                Text(text = "удалить", fontSize = 14.sp)
            }
        }
    } else {
        Text(text = "Ваша корзина пуста", fontSize = 18.sp)
    }
    


}