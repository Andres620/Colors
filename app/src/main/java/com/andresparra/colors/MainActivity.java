package com.andresparra.colors;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  TextView lblTargetColor = null;
    private TextView lblProposedColor = null;
    private SeekBar sbrRed = null;
    private SeekBar sbrGreen = null;
    private SeekBar sbrBlue = null;
    private TextView lblRedValue = null;
    private TextView lblGreenValue = null;
    private TextView lblBlueValue = null;
    private Button btnGetScore = null;
    private Button btnNewColor = null;

    private ColorsGame colorsGame = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // R es una clase que se genera automaticamente con los identificadores de todos los elementos
        setContentView(R.layout.activity_main2);

        initView();

        colorsGame = new ColorsGame();

        colorsGame.setOnChangeTargetColorListener((newBackColor, newTextColor) -> {
            lblTargetColor.setBackgroundColor(newBackColor);
            lblTargetColor.setTextColor(newTextColor);
        });

        colorsGame.setOnChangeProposedColorListener((newBackColor, newTextColor) -> {
            lblProposedColor.setBackgroundColor(newBackColor);
            lblProposedColor.setTextColor(newTextColor);

            sbrRed.setProgress(Color.red(newBackColor));
            lblRedValue.setText(String.valueOf(Color.red(newBackColor)));
            sbrGreen.setProgress(Color.green(newBackColor));
            lblGreenValue.setText(String.valueOf(Color.green(newBackColor)));
            sbrBlue.setProgress(Color.blue(newBackColor));
            lblBlueValue.setText(String.valueOf(Color.blue(newBackColor)));
        });

        restartGame();
        initEvents();
    }

    public void  initView(){
        lblTargetColor = findViewById(R.id.lblTargetColor);
        lblProposedColor = findViewById(R.id.lblProposedColor);

        sbrRed = findViewById(R.id.sbrRed);
        sbrGreen = findViewById(R.id.sbrGreen);
        sbrBlue = findViewById(R.id.sbrBlue);

        lblRedValue = findViewById(R.id.lblRedValue);
        lblGreenValue = findViewById(R.id.lblGreenValue);
        lblBlueValue = findViewById(R.id.lblBlueValue);

        btnGetScore = findViewById(R.id.btnGetScore);
        btnNewColor = findViewById(R.id.btnNewColor);
    }

    public void initEvents(){
        SeekBar[] seekBars = {sbrRed, sbrGreen, sbrBlue};

        for(SeekBar seekBar: seekBars){

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updatesValues();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }

        btnGetScore.setOnClickListener(view -> showScore());
        btnNewColor.setOnClickListener(view -> restartGame());
    }

    public void restartGame(){
        colorsGame.restartGame();
    }

    public void updatesValues(){

        int redValue = sbrRed.getProgress();
        int greenValue = sbrGreen.getProgress();
        int blueValue = sbrBlue.getProgress();
        int newBackColor = Color.rgb(redValue, greenValue, blueValue);

        colorsGame.setProposedBackColor(newBackColor);
    }

    public void showScore(){
        final String RED = getString(R.string.Red);
        final String GREEN = getString(R.string.Green);
        final String BLUE = getString(R.string.Blue);
        final String VERY_LOW = getString(R.string.Very_low);
        final String LOW = getString(R.string.Low);
        final String VERY_HIGH = getString(R.string.Very_high);
        final String HIGH = getString(R.string.High);

        int targetColor = colorsGame.getTargetBackColor();
        int proposedColor = colorsGame.getProposedBackColor();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        StringBuilder text = new StringBuilder();
        StringBuilder tips = new StringBuilder();

        int redDiff = Color.red(targetColor) - Color.red(proposedColor);
        int greenDiff = Color.green(targetColor) - Color.green(proposedColor);
        int blueDiff = Color.blue(targetColor) - Color.blue(proposedColor);

        text.append(getString(R.string.Your_score_is, String.valueOf(colorsGame.getScore())));

        if (redDiff > 10){
            tips.append("\n");
            tips.append(getString(R.string.X_is_Y, RED.toLowerCase(), VERY_LOW.toLowerCase()));
        }else if (redDiff > 0){
            tips.append("\n");
            tips.append(getString(R.string.X_is_Y, RED.toLowerCase(), LOW.toLowerCase()));
        }else if (redDiff < -10){
            tips.append("\n");
            tips.append(getString(R.string.X_is_Y, RED.toLowerCase(), VERY_HIGH.toLowerCase()));
        }else if (redDiff < 0){
            tips.append("\n");
            tips.append(getString(R.string.X_is_Y, RED.toLowerCase(), HIGH.toLowerCase()));
        }

        if (greenDiff > 10){
            tips.append("\n");
            tips.append(getString(R.string.X_is_Y, GREEN.toLowerCase(), VERY_LOW.toLowerCase()));
        }else if (greenDiff > 0){
            tips.append("\n");
            tips.append(getString(R.string.X_is_Y, GREEN.toLowerCase(), LOW.toLowerCase()));
        }else if (greenDiff < -10){
            tips.append("\n");
            tips.append(getString(R.string.X_is_Y, GREEN.toLowerCase(), VERY_HIGH.toLowerCase()));
        }else if (greenDiff < 0){
            tips.append("\n");
            tips.append(getString(R.string.X_is_Y, GREEN.toLowerCase(), HIGH.toLowerCase()));
        }

        if (blueDiff > 10){
            tips.append("\n");
            tips.append(getString(R.string.X_is_Y, BLUE.toLowerCase(), VERY_LOW.toLowerCase()));
        }else if (blueDiff > 0){
            tips.append("\n");
            tips.append(getString(R.string.X_is_Y, BLUE.toLowerCase(), LOW.toLowerCase()));
        }else if (blueDiff < -10){
            tips.append("\n");
            tips.append(getString(R.string.X_is_Y, BLUE.toLowerCase(), VERY_HIGH.toLowerCase()));
        }else if (blueDiff < 0){
            tips.append("\n");
            tips.append(getString(R.string.X_is_Y, BLUE.toLowerCase(), HIGH.toLowerCase()));
        }

        if (tips.length() > 0){
            text.append("\n\n");
            text.append(getString(R.string.Tips));
            text.append(": ");
            text.append(tips);
        }

        alert.setMessage(text);
        alert.setPositiveButton(getString(R.string.Close), null);

        alert.show();
    }
}