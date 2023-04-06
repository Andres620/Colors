package com.andresparra.colors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val colorsGame = ColorsGame()

        super.onCreate(savedInstanceState)
        setContent {
            myUI(colorsGame = colorsGame)
        }
    }
}

@Composable
fun myUI(colorsGame: ColorsGame){
    var targetColor by remember { mutableStateOf(Color.Black) }
    var proposedColor by remember { mutableStateOf(Color.Black) }
    var score by remember { mutableStateOf(0) }

    colorsGame.setOnChangeTargetColorListener { newBackColor, newTextColor ->
        targetColor = Color(newBackColor)
    }

    colorsGame.setOnChangeProposedColorListener { newBackColor, newTextColor ->
        proposedColor = Color(newBackColor)
    }



    var restartGame = { colorsGame.restartGame() }

    var targetTextColor by remember { mutableStateOf(Color.Black) }
    var proposedTextColor by remember { mutableStateOf(Color.Black) }


    var sliRedValue by remember {
        mutableStateOf(128f)
    }
    var sliGreenValue by remember {
        mutableStateOf(128f)
    }
    var sliBlueValue by remember {
        mutableStateOf(128f)
    }

    Column(
        modifier = Modifier
            .padding(10.dp)
    ){
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxHeight())
        {
            colorSection(proposedColor, targetColor)
        }
        sliderSection(
            title = stringResource(R.string.Red),
            color = Color.Red,
            value = sliRedValue,
            onValueChange = { newValue -> sliRedValue = newValue },
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
        buttonSection(title = "Score", event = restartGame) //CAMBIAR
        buttonSection(title = "New" , event = restartGame)
        updateValues(sliRedValue.toInt(), sliGreenValue.toInt(), sliBlueValue.toInt(), colorsGame)
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
fun colorSection(proposedColor: Color, targetColor: Color){
    Row(
        verticalAlignment = Alignment.CenterVertically,

    ){
        Column(modifier = Modifier
                .weight(1f))
        {
            Text(text = stringResource(R.string.Proposed_color), Modifier
                .padding(5.dp)
                .wrapContentWidth(Alignment.Start)
                .background(proposedColor)
                .fillMaxSize()
            )
        }
        Column(modifier = Modifier
            .weight(1f))
        {
            Text(text = stringResource(R.string.Proposed_color), Modifier
                .padding(5.dp)
                .wrapContentWidth(Alignment.Start)
                .background(targetColor)
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
    title:String,
    event: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ){
        Button(onClick = { event }) {
            Text(text = title, Modifier
                .fillMaxWidth())
        }
    }
}

fun updateValues(redValue: Int, greenValue: Int, blueValue: Int, colorsGame: ColorsGame) {
    val newBackColor = Color(red = redValue, green = greenValue, blue = blueValue)
    colorsGame.setProposedBackColor(newBackColor.toArgb())
}
