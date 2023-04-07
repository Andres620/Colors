package com.andresparra.colors

import android.app.AlertDialog
import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.text.style.TextAlign
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red


class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val colorsGame = ColorsGame()
        super.onCreate(savedInstanceState)
        setContent {
            myUI(colorsGame = colorsGame, this)
        }
    }
}

@Composable
fun myUI(colorsGame: ColorsGame, context: Context){
    var targetColor by remember { mutableStateOf(Color(ColorsGame.randomColor())) }
    var proposedColor by remember { mutableStateOf(Color(ColorsGame.randomColor())) }
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
        buttonSection(title =  stringResource(R.string.Score), event = { showScore(colorsGame, context) } )//CAMBIAR
        buttonSection(title = stringResource(R.string.New) , event = {colorsGame.restartGame()})
        updateValues(sliRedValue.toInt(), sliGreenValue.toInt(), sliBlueValue.toInt(), colorsGame)
    }

}

@Composable
fun MyScreen() {
    val context = LocalContext.current
    myUI(ColorsGame(), context)
}

@Preview(showBackground = true)
@Composable
fun defaultPreview(){
    val context = LocalContext.current
    myUI(ColorsGame(), context)
}

@Composable
fun colorSection(proposedColor: Color, targetColor: Color){
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f))
        {
            Text(text = stringResource(R.string.Proposed_color), Modifier
                .padding(5.dp)
                .background(proposedColor)
                .fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
            .weight(1f))
        {
            Text(text = stringResource(R.string.Target_color), Modifier
                .padding(5.dp)
                .background(targetColor)
                .fillMaxSize(),
                textAlign = TextAlign.Center
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
        Button(onClick = { event.invoke() }) {
            Text(text = title, Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center)
        }
    }
}

fun updateValues(redValue: Int, greenValue: Int, blueValue: Int, colorsGame: ColorsGame) {
    val newBackColor = Color(red = redValue, green = greenValue, blue = blueValue)
    colorsGame.setProposedBackColor(newBackColor.toArgb())
}

fun showScore(colorsGame: ColorsGame, context: Context) {
    val RED = context.getString(R.string.Red)
    val GREEN = context.getString(R.string.Green)
    val BLUE = context.getString(R.string.Blue)
    val VERY_LOW = context.getString(R.string.Very_low)
    val LOW = context.getString(R.string.Low)
    val VERY_HIGH = context.getString(R.string.Very_high)
    val HIGH = context.getString(R.string.High)

    val targetColor = colorsGame.targetBackColor
    val proposedColor = colorsGame.proposedBackColor

    val alert = AlertDialog.Builder(context)
    val text = StringBuilder()
    val tips = StringBuilder()

    val redDiff = targetColor.red - proposedColor.red
    val greenDiff = targetColor.green - proposedColor.green
    val blueDiff = targetColor.blue - proposedColor.blue

    text.append(context.getString(R.string.Your_score_is, colorsGame.score.toString()))

    if (redDiff > 10) {
        tips.append("\n")
        tips.append(context.getString(R.string.X_is_Y, RED, VERY_LOW))
    } else if (redDiff > 0) {
        tips.append("\n")
        tips.append(context.getString(R.string.X_is_Y, RED, LOW))
    } else if (redDiff < -10) {
        tips.append("\n")
        tips.append(context.getString(R.string.X_is_Y, RED, VERY_HIGH))
    } else if (redDiff < 0) {
        tips.append("\n")
        tips.append(context.getString(R.string.X_is_Y, RED, HIGH))
    }

    if (greenDiff > 10) {
        tips.append("\n")
        tips.append(context.getString(R.string.X_is_Y, GREEN, VERY_LOW))
    } else if (greenDiff > 0) {
        tips.append("\n")
        tips.append(context.getString(R.string.X_is_Y, GREEN, LOW))
    } else if (greenDiff < -10) {
        tips.append("\n")
        tips.append(context.getString(R.string.X_is_Y, GREEN, VERY_HIGH))
    } else if (greenDiff < 0) {
        tips.append("\n")
        tips.append(context.getString(R.string.X_is_Y, GREEN, HIGH))
    }

    if (blueDiff > 10) {
        tips.append("\n")
        tips.append(context.getString(R.string.X_is_Y, BLUE, VERY_LOW))
    } else if (blueDiff > 0) {
        tips.append("\n")
        tips.append(context.getString(R.string.X_is_Y, BLUE, LOW))
    } else if (blueDiff < -10) {
        tips.append("\n")
        tips.append(context.getString(R.string.X_is_Y, BLUE, VERY_HIGH))
    } else if (blueDiff < 0) {
        tips.append("\n")
        tips.append(context.getString(R.string.X_is_Y, BLUE, HIGH))
    }

    if (tips.length > 0) { // tips.length > 0
        text.append("\n\n")
        text.append(context.getString(R.string.Tips))
        text.append(": ")
        text.append(tips)
    }

    alert.setMessage(text.toString())
    alert.setPositiveButton(context.getString(R.string.Close), null)

    alert.show()
}
