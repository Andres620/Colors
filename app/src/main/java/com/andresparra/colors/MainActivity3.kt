package com.andresparra.colors

import android.os.Bundle
import android.widget.SeekBar
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myUI(ColorsGame())
        }
    }
}

@Composable
fun myUI(colorsGame: ColorsGame){
    var backgroundColor by remember { mutableStateOf(Color.White) }
    var sliRedValue by remember {
        mutableStateOf(128f)
    }
    var sliGreenValue by remember {
        mutableStateOf(128f)
    }
    var sliBlueValue by remember {
        mutableStateOf(128f)
    }
    var proposedColor = ColorsGame.randomColor()

    Column(
        modifier = Modifier
            .padding(10.dp)
    ){
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxHeight()) {
        colorSection()
        }
        sliderSection(
            title = stringResource(R.string.Red),
            color = Color.Red,
            value = sliRedValue,
            onValueChange = { newValue -> sliRedValue = newValue},
        )
        sliderSection(
            title = stringResource(R.string.Green),
            color = Color.Green,
            value = sliGreenValue,
            onValueChange = { newValue -> sliGreenValue = newValue}
        )
        sliderSection(
            title = stringResource(R.string.Blue),
            color = Color.Blue,
            value = sliBlueValue,
            onValueChange = { newValue -> sliBlueValue = newValue}
        )
        buttonSection(title = "Score")
        buttonSection(title = "New")
    }

}

@Composable
fun MyScreen() {
    myUI(ColorsGame())
}

@Preview(showBackground = true)
@Composable
fun defaultPreview(){
    myUI(ColorsGame())
}

@Composable
fun colorSection(){
    Row(
        verticalAlignment = Alignment.CenterVertically,

    ){
        Column(modifier = Modifier
                .weight(1f))
        {
            Text(text = stringResource(R.string.Proposed_color), Modifier
                .padding(5.dp)
                .wrapContentWidth(Alignment.Start)
                .background(Color(ColorsGame.randomColor()))
                .fillMaxSize()
            )
        }
        Column(modifier = Modifier
            .weight(1f))
        {
            Text(text = stringResource(R.string.Proposed_color), Modifier
                .padding(5.dp)
                .wrapContentWidth(Alignment.Start)
                .background(Color(ColorsGame.randomColor()))
                .fillMaxSize()
            )
        }

    }
}

@Composable
fun sliderSection(
    title: String,
    color: Color,
    value: Float,
    onValueChange: (Float) -> Unit
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
            onValueChange = onValueChange,
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


