package com.andresparra.colors;

import android.graphics.Color;

import java.util.Random;

public class ColorsGame {
    private int targetBackColor = 0;
    private int targetTextColor = 0;
    private int proposedBackColor = 0;
    private int proposedTextColor = 0;
    private onChangeTargetColorListener onChangeTargetColorListener;
    private onChangeProposedColorListener onChangeProposedColorListener;

    public ColorsGame(){
        restartGame();
    }

    public void restartGame(){
        setTargetBackColor(randomColor());
    }

    //Return the distance between two colors
    public static double distance(int color1, int color2){
        int redVal1 = Color.red(color1);
        int greenVal1 = Color.green(color1);
        int blueVal1 = Color.blue(color1);

        int redVal2 = Color.red(color2);
        int greenVal2 = Color.green(color2);
        int blueVal2 = Color.blue(color2);

        //Partial results
        int resRedVal = (int)(Math.pow((redVal1 - redVal2), 2));
        int resGreenVal = (int)(Math.pow((greenVal1 - greenVal2), 2));
        int resBlueVal = (int)(Math.pow((blueVal1 - blueVal2), 2));

        // Calculate the distance between two colors (points 3D)
        double distance = Math.sqrt(resRedVal + resGreenVal + resBlueVal);

        return distance;
    }

    // Return a score between 0 and 100
    public int getScore(){
        double distance = this.distance(targetBackColor, proposedBackColor);

        int score = (int)(100 - Math.min(distance, 100));

        return score;
    }

    //Return a suggested text color (black or white)
    public static int getSuggestedTextColor(int backColor){
        int redVal = Color.red(backColor);
        int greenVal = Color.green(backColor);
        int blueVal = Color.blue(backColor);
        int grayVal = (int)(redVal * 0.20 + greenVal * 0.75 + blueVal * 0.05);
        int textColor = Color.BLACK;

        if (255 - grayVal > grayVal){
            textColor = Color.WHITE;
        }

        return textColor;
    }

    //return a random color
    public static int randomColor(){
        Random rand = new Random();

        int redVal = rand.nextInt(256);
        int greenVal = rand.nextInt(256);
        int blueVal = rand.nextInt(256);
        int color = Color.rgb(redVal, greenVal, blueVal);

        return color;
    }

    public int getTargetBackColor(){
        return targetBackColor;
    }

    private void setTargetBackColor(int newBackColor){
        targetBackColor = newBackColor;
        targetTextColor = getSuggestedTextColor(targetBackColor);

        do {
            proposedBackColor = randomColor();
        }
        while (getScore() > 0);

        proposedTextColor = getSuggestedTextColor(proposedBackColor);

        if (getOnChangeTargetColorListener() != null){
            getOnChangeTargetColorListener().onChange(targetBackColor, targetTextColor);
        }

        if (getOnChangeProposedColorListener() != null){
            getOnChangeProposedColorListener().onChange(proposedBackColor, proposedTextColor);
        }
    }

    public int getTargetTextColor(){
        return  targetTextColor;
    }

    public void setTargetTextColor(int targetTextColor){ this.targetTextColor = targetTextColor;}

    public int getProposedBackColor(){
        return proposedBackColor;
    }

    public void setProposedBackColor(int newBackColor){
        proposedBackColor = newBackColor;
        proposedTextColor = getSuggestedTextColor(proposedBackColor);

        if (getOnChangeProposedColorListener() != null){
            getOnChangeProposedColorListener().onChange(proposedBackColor, proposedTextColor);
        }
    }

    public int getProposedTextColor(){
        return proposedTextColor;
    }

    public void setProposedTextColor(int proposedTextColor) {
        this.proposedTextColor = proposedTextColor;
    }

    public onChangeTargetColorListener getOnChangeTargetColorListener() {
        return onChangeTargetColorListener;
    }

    public void setOnChangeTargetColorListener(onChangeTargetColorListener newDelegate) {
        this.onChangeTargetColorListener = newDelegate;
    }


    public onChangeProposedColorListener getOnChangeProposedColorListener() {
        return onChangeProposedColorListener;
    }

    public void setOnChangeProposedColorListener(onChangeProposedColorListener newDelegate) {
        this.onChangeProposedColorListener = newDelegate;
    }

    public interface onChangeTargetColorListener{
        public abstract void onChange(int backColor, int textColor);
    }

    public interface onChangeProposedColorListener{
        public abstract void onChange(int backColor, int textColor);
    }
}
