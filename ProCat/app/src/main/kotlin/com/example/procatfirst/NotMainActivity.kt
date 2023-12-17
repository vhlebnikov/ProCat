package com.example.procatfirst

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.procatfirst.api.ApiCalls
import com.example.procatfirst.data_storage.DataCoordinator
import com.example.procatfirst.intents.NotificationCoordinator
import com.example.procatfirst.intents.SystemNotifications
import com.example.procatfirst.intents.sendIntent
import com.example.procatfirst.ui.CustomComposeTextWithReceiver


class NotMainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        initMainPageCompose()
        initBackground()

    }

    private fun initBackground() {
        NotificationCoordinator.shared.initialize(baseContext)

        DataCoordinator.shared.initialize(
            context = baseContext,
            onLoad = {
                //DataCoordinator.shared.updateUserEmail(DataCoordinator.shared.defaultUserEmailPreferenceValue)
            }
        )
    }


    private fun secondPageInit() {
        // setContentView(R.layout.right_menu)
        // super.findViewById<Button>(R.id.backButton).setOnClickListener {
       // initMainPageCompose()
        //  }
   }


    private fun initMainPageCompose() {

        setContent {
            MyHeader()
            TestButtons()
        }

    }


    @Composable
    private fun MyHeader() {
        TopAppBar(backgroundColor = Color.Magenta) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "ProCat app", fontSize = 22.sp)
                Spacer(Modifier.size(60.dp))
                FloatingActionButton(onClick = { secondPageInit() }) {
                    Image(
                        painter = painterResource(id = androidx.appcompat.R.drawable.abc_ic_arrow_drop_right_black_24dp),
                        contentDescription = "desk",
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
    }


    @Composable
    private fun TestButtons() {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hello world!!!!!!!", fontSize = 25.sp)
            Spacer(Modifier.size(5.dp))
            Text(text = "It's column", fontSize = 25.sp)
            Spacer(Modifier.size(5.dp))
            var clickCnt by remember { mutableStateOf(0) }

            Button(onClick = { createToast("Hello toast") }) {
                Text(text = "Show toast", fontSize = 25.sp)
            }
            Spacer(Modifier.size(5.dp))
            Button(onClick = { clickCnt++ }) {
                Text(text = "Click button", fontSize = 25.sp)
            }
            Spacer(Modifier.size(5.dp))
            Text(text = "Clicked: $clickCnt times", fontSize = 25.sp)
            Spacer(Modifier.size(15.dp))

            Button(
                onClick = { runApi("https://randomuser.me/") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Api call", fontSize = 24.sp)
            }
            Button(onClick = { NotificationCoordinator.shared.sendIntent(SystemNotifications.myTestIntent) }) {
                Text(text = "Test intent", fontSize = 24.sp)
            }

            CustomComposeTextWithReceiver()

        }
    }

    private fun createToast(body: String?) {
        val myToast = Toast.makeText(this, body, Toast.LENGTH_SHORT)
        myToast.show()
    }

    private fun runApi(url: String) {
        ApiCalls.shared.runApi(url)
        //PostApi.shared.rawJSON()
        //ApiCalls.shared.postApi("http://dummy.restapiexample.com")
    }

}
