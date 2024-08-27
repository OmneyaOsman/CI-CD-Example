package com.omneya.ci_cd_example

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.omneya.ci_cd_example.ui.theme.CICDExampleTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCenter.start(
            application, "aba66190-e29c-408b-ac89-0250763278d7",
            Analytics::class.java, Crashes::class.java
        )
        AppCenter.setLogLevel(Log.VERBOSE);
        enableEdgeToEdge()
        setContent {
            CICDExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "CI/CD Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column (verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()){
        Text(
            text = "Hello Hello $name!",
            modifier = modifier.clickable {
                Analytics.trackEvent("My custom event");
                //            Crashes.generateTestCrash()
                //            throw Exception("noo")
            }
        )
        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription ="Image" )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CICDExampleTheme {
        Greeting("Android")
    }
}