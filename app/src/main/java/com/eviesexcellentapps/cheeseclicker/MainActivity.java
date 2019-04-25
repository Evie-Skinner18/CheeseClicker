package com.eviesexcellentapps.cheeseclicker;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    private int currentScore = 0;
    private TextView timerText;
    private CountDownTimer countDownTimer;
    private boolean isRunning = false;
    private boolean hasTimerCompleted = false;

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

                    // Q for coach: how do I temporarily change the text colour like flash up as a different colour when they get a bonus point?
                    //textViewScore.setTextColor(#335222);
                }
            }
        });

        Button button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

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



        countDownTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (isRunning)
                {
                    timerText.setText("seconds remaining: " + millisUntilFinished / 1000);
                }
            }

            public void onFinish() {
                timerText.setText("done!");
                isRunning = false;
                hasTimerCompleted = true;

            }
        };

        // separate logic in onCreate from the creation of resources
                initResources();



    }
}
