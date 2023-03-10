package com.andresparra.colors

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.andresparra.colors.ColorsGame.onChangeProposedColorListener
import com.andresparra.colors.ColorsGame.onChangeTargetColorListener

class MainActivity2 : AppCompatActivity() {
    private var lblTargetColor: TextView? = null
    private var lblProposedColor: TextView? = null
    private var sbrRed: SeekBar? = null
    private var sbrGreen: SeekBar? = null
    private var sbrBlue: SeekBar? = null
    private var lblRedValue: TextView? = null
    private var lblGreenValue: TextView? = null
    private var lblBlueValue: TextView? = null
    private var btnGetScore: Button? = null
    private var btnNewColor: Button? = null

    private var colorsGame: ColorsGame? = null

    fun initView() {
        lblTargetColor = findViewById(R.id.lblTargetColor)
        lblProposedColor = findViewById(R.id.lblProposedColor)
        sbrRed = findViewById(R.id.sbrRed)
        sbrGreen = findViewById(R.id.sbrGreen)
        sbrBlue = findViewById(R.id.sbrBlue)
        lblRedValue = findViewById(R.id.lblRedValue)
        lblGreenValue = findViewById(R.id.lblGreenValue)
        lblBlueValue = findViewById(R.id.lblBlueValue)
        btnGetScore = findViewById(R.id.btnGetScore)
        btnNewColor = findViewById(R.id.btnNewColor)
    }

    fun restartGame() {
        colorsGame!!.restartGame();
    }

    fun updatesValues() {
        val redValue = sbrRed!!.progress
        val greenValue = sbrGreen!!.progress
        val blueValue = sbrBlue!!.progress
        val newBackColor = Color.rgb(redValue, greenValue, blueValue)
        colorsGame!!.proposedBackColor = newBackColor
    }

    fun showScore() {
        val RED = "Red" //getString(R.string.Red)
        val GREEN = "Green" //getString(R.string.Green)
        val BLUE = "Blue" //getString(R.string.Blue)
        val VERY_LOW = "Very low" //getString(R.string.Very_low)
        val LOW = "Low"//getString(R.string.Low)
        val VERY_HIGH = "Very high" //getString(R.string.Very_high)
        val HIGH = "High" //getString(R.string.High)
        val targetColor = colorsGame!!.targetBackColor
        val proposedColor = colorsGame!!.proposedBackColor
        val alert = AlertDialog.Builder(this)
        val text = StringBuilder()
        val tips = StringBuilder()
        val redDiff = Color.red(targetColor) - Color.red(proposedColor)
        val greenDiff = Color.green(targetColor) - Color.green(proposedColor)
        val blueDiff = Color.blue(targetColor) - Color.blue(proposedColor)
        text.append("Your score is {colorsGame!!.score.toString()}") //(getString(R.string.Your_score_is, colorsGame.score.toString()))
        if (redDiff > 10) {
            tips.append("\n")
            tips.append("{RED.lowercase(Locate.getDefault())} is {VERY_LOW.lowercase(Locale.getDefault())}") //(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), VERY_LOW.lowercase(Locale.getDefault())))
        } else if (redDiff > 0) {
            tips.append("\n")
            tips.append("{RED.lowercase(Locate.getDefault())} is {LOW.lowercase(Locale.getDefault())}") //(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), LOW.lowercase(Locale.getDefault())))
        } else if (redDiff < -10) {
            tips.append("\n")
            tips.append("{RED.lowercase(Locate.getDefault())} is {VERY_HIGH.lowercase(Locale.getDefault())}")  //(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), VERY_HIGH.lowercase(Locale.getDefault())))
        } else if (redDiff < 0) {
            tips.append("\n")
            tips.append("{RED.lowercase(Locate.getDefault())} is {HIGH.lowercase(Locale.getDefault())}")  //(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), HIGH.lowercase(Locale.getDefault())))
        }

        if (greenDiff > 10) {
            tips.append("\n")
            tips.append("{GREEN.lowercase(Locate.getDefault())} is {VERY_LOW.lowercase(Locale.getDefault())}") //(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), VERY_LOW.lowercase(Locale.getDefault())))
        } else if (greenDiff > 0) {
            tips.append("\n")
            tips.append("{GREEN.lowercase(Locate.getDefault())} is {LOW.lowercase(Locale.getDefault())}") //(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), LOW.lowercase(Locale.getDefault())))
        } else if (greenDiff < -10) {
            tips.append("\n")
            tips.append("{GREEN.lowercase(Locate.getDefault())} is {VERY_HIGH.lowercase(Locale.getDefault())}")  //(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), VERY_HIGH.lowercase(Locale.getDefault())))
        } else if (greenDiff < 0) {
            tips.append("\n")
            tips.append("{GREEN.lowercase(Locate.getDefault())} is {HIGH.lowercase(Locale.getDefault())}")  //(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), HIGH.lowercase(Locale.getDefault())))
        }

        if (blueDiff > 10) {
            tips.append("\n")
            tips.append("{BLUE.lowercase(Locate.getDefault())} is {VERY_LOW.lowercase(Locale.getDefault())}") //(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), VERY_LOW.lowercase(Locale.getDefault())))
        } else if (blueDiff > 0) {
            tips.append("\n")
            tips.append("{BLUE.lowercase(Locate.getDefault())} is {LOW.lowercase(Locale.getDefault())}") //(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), LOW.lowercase(Locale.getDefault())))
        } else if (blueDiff < -10) {
            tips.append("\n")
            tips.append("{BLUE.lowercase(Locate.getDefault())} is {VERY_HIGH.lowercase(Locale.getDefault())}")  //(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), VERY_HIGH.lowercase(Locale.getDefault())))
        } else if (blueDiff < 0) {
            tips.append("\n")
            tips.append("{BLUE.lowercase(Locate.getDefault())} is {HIGH.lowercase(Locale.getDefault())}")  //(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), HIGH.lowercase(Locale.getDefault())))
        }

        if (tips.length > 0) {
            text.append("\n\n")
            text.append("Tips")
            text.append(": ")
            text.append(tips)
        }
        alert.setMessage(text)
        alert.setPositiveButton(getString(R.string.Close), null)
        alert.show()
    }


    fun initEvents() {
        val seekBars = arrayOf(sbrRed, sbrGreen, sbrBlue)
        for (seekBar in seekBars) {
            seekBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    updatesValues()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })
        }
        btnGetScore!!.setOnClickListener { view: View? -> showScore() }
        btnNewColor!!.setOnClickListener { view: View? -> restartGame() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // R es una clase que se genera automaticamente con los identificadores de todos los elementos
        setContentView(R.layout.activity_main)
        initView()
        colorsGame = ColorsGame()
        colorsGame!!.onChangeTargetColorListener = onChangeTargetColorListener {
            newBackColor: Int, newTextColor: Int ->
            lblProposedColor!!.setBackgroundColor(newBackColor)
            lblProposedColor!!.setTextColor(newTextColor)
        }
        colorsGame!!.onChangeProposedColorListener = onChangeProposedColorListener {
            newBackColor: Int, newTextColor: Int ->
            lblProposedColor!!.setBackgroundColor(newBackColor)
            lblProposedColor!!.setTextColor(newTextColor)
            sbrRed!!.progress = Color.red(newBackColor)
            lblRedValue!!.text = Color.red(newBackColor).toString()
            sbrGreen!!.progress = Color.green(newBackColor)
            lblGreenValue!!.text = Color.green(newBackColor).toString()
            sbrBlue!!.progress = Color.blue(newBackColor)
            lblBlueValue!!.text = Color.blue(newBackColor).toString()
        }
        restartGame()
        initEvents()
    }
}