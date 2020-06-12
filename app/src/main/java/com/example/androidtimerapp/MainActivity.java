package com.example.androidtimerapp;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekbar;
    TextView timerTextView;
    boolean counterIsActive=false;
    Button controllerButton;
    CountDownTimer countDownTimer;




    public void resetTimer(){
        timerTextView.setText("0:30");
        timerSeekbar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("GO!");
        timerSeekbar.setEnabled(true);
        counterIsActive=false;
    }





    public void updateTimer(int secondsLeft){
        int minutes= (int) secondsLeft/60;
        int seconds= secondsLeft- minutes *60;
        String secondString= Integer.toString(seconds);
        if(secondString=="0"){
            secondString="0";
        }
        else if(seconds<=9){





            secondString="0"+secondString;
        }


        timerTextView.setText(Integer.toString(minutes)+":"+ secondString);

        }





    public void controlTimer(View view) {
        if (counterIsActive == false) {
            counterIsActive = true;
            timerSeekbar.setEnabled(false);
            controllerButton.setText("STOP!");

            countDownTimer=new CountDownTimer(timerSeekbar.getProgress() * 1000 + 100, 1000) {
                public void onTick(long milliuntilFinished) {
                    updateTimer((int) milliuntilFinished / 1000);


                }

                @Override
                public void onFinish() {
                    resetTimer();
                    timerTextView.setText("0:00");
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();

                }
            }.start();
        }
        else{
            resetTimer();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekbar= (SeekBar) findViewById(R.id.timerSeekbar);
        timerTextView =(TextView) findViewById(R.id.timerTextView);
        controllerButton=(Button) findViewById(R.id.controllerButton);
        timerSeekbar.setMax(600);
        timerSeekbar.setProgress(30);
        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               updateTimer(progress);



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
