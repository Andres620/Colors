package com.andresparra.colors

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.*
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ){
        colorSection();
        sliderSection(title = stringResource(R.string.Red), color = Color.Red, value = 128f)
        sliderSection(title = stringResource(R.string.Green), color = Color.Green, value = 128f)
        sliderSection(title = stringResource(R.string.Blue), color = Color.Blue, value = 128f)
        buttonSection(title = "Score")
        buttonSection(title = "New")
    }

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

@Composable
fun colorSection(){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = stringResource(R.string.Proposed_color), Modifier
            .weight(1f)
            .padding(5.dp)
            .wrapContentWidth(Alignment.Start)
            .background(Color.Cyan)
            .fillMaxWidth()
            .height(570.dp)
        )

        Text(text = stringResource(R.string.Target_color), Modifier
            .weight(1f)
            .padding(5.dp)
            .wrapContentWidth(Alignment.End)
            .background(Color.Green)
            .fillMaxWidth()
            .height(570.dp)
        )
    }
}

@Composable
fun sliderSection(
    title: String,
    color: Color,
    value: Float
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
    ){
        Text(text = title, Modifier
            .width(40.dp))
        Slider(
            value = value,
            onValueChange = {},
            valueRange = 0f..255f,
            colors = SliderDefaults.colors(
                thumbColor = color,
                activeTickColor = color,
                inactiveTickColor = Color.Gray,
            ),
            modifier = Modifier
                .weight(1f)
        )
        Text(text = value.toInt().toString())
    }



}

@Composable
fun buttonSection(
    title:String
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ){
        Button(onClick = { /*TODO*/ }) {
            Text(text = title, Modifier
                .fillMaxWidth())
        }
    }
}





