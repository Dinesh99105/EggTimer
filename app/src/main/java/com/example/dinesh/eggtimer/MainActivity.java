package com.example.dinesh.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;

    SeekBar TimerSeekBar;

    Boolean TimerIsActive = false;

    Button Controllerbutton;

    CountDownTimer countDownTimer;

    public void resetTimer(){

        timerTextView.setText("0:30");
        TimerSeekBar.setProgress(30);
        countDownTimer.cancel();
        Controllerbutton.setText("Go!");
        TimerSeekBar.setEnabled(true);
        TimerIsActive = false;
    }

    public void updateTimer(int secondsLeft){

        int minutes = (int) secondsLeft/60;

        int seconds =  secondsLeft-minutes*60;

        String secondsString = Integer.toString(seconds);

        if(seconds <= 9){

            secondsString = "0" + secondsString ;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondsString);


    }

    public void controlTimer(View view){

        if(TimerIsActive==false) {

            TimerIsActive = true;

            TimerSeekBar.setEnabled(false);

            Controllerbutton.setText("Stop");

            countDownTimer =new CountDownTimer(TimerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {


                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);

                    mplayer.start();

                    resetTimer();

                }
            }.start();
        }else{

             resetTimer();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerSeekBar = (SeekBar) findViewById(R.id.TimerSeekBar);

        timerTextView = (TextView) findViewById(R.id.timerTextView);

        Controllerbutton = (Button) findViewById(R.id.Controllerbutton);

        TimerSeekBar.setMax(600);

        TimerSeekBar.setProgress(30);

        TimerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
