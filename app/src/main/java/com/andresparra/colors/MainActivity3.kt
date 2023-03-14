package com.andresparra.colors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.andresparra.colors.ui.theme.ColorsTheme

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myUI()
        }
    }
}


@Composable
fun myUI(){
    Text(text = "Hello World")
}

@Preview(showBackground = true)
@Composable
fun defaultPreview(){
    myUI()
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
