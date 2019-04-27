package com.eviesexcellentapps.cheeseclicker;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private int currentScore = 0;
    private TextView timerText;
    private CountDownTimer countDownTimer;
    private boolean isRunning = false;
    private boolean hasTimerCompleted = false;
    // array of nine values to turn the score a random RGB colour every time the user reaches multiple of 10 points
    private int[] scoreColours = {

            204,
            0,
            201,
            50,
            25,
            255,
            100,
            63,
            144
    };


    protected void initResources() {
        //        set up the var that will be the cheese and also the var for the score rendered in the xml
        ImageView cheese = findViewById(R.id.imageCheese);
        final TextView textViewScore = findViewById(R.id.score);
        timerText = findViewById(R.id.timer);

        // create onclick listener for the cheese and tell Java what to do when the cheese gets clicked
        cheese.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (!isRunning && hasTimerCompleted)
                {
                    return;
                }

                currentScore++;
                textViewScore.setText(String.valueOf(currentScore));

                if (currentScore % 10 == 0)
                {
                    Toast.makeText(MainActivity.this, "Bonus point!", Toast.LENGTH_SHORT).show();
                    currentScore++;

                    // Java will pick random num between 0 and 8 three times. Each num will correspond to the index of an item in the scoreColours array
                    int firstRandomNum = ( new Random()).nextInt(9);
                    int secondRandomNum = ( new Random()).nextInt(9);
                    int thirdRandomNum = ( new Random()).nextInt(9);

                    textViewScore.setTextColor(Color.rgb(scoreColours[firstRandomNum], scoreColours[secondRandomNum], scoreColours[thirdRandomNum]));
                }
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                          currentScore = 0;
                                          countDownTimer.start();
                                          isRunning = true;
                                      }
                                  }
        );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownTimer = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (isRunning)
                {
                    timerText.setText("Seconds remaining: " + millisUntilFinished / 1000);
                }
            }

            public void onFinish() {
                timerText.setText("Time's up!");
                isRunning = false;
                hasTimerCompleted = true;

            }
        };

        // separate logic in onCreate from the creation of resources
                initResources();

    }
}
