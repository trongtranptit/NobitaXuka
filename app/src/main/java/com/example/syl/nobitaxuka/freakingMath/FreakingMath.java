package com.example.syl.nobitaxuka.freakingMath;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.syl.nobitaxuka.R;
import com.example.syl.nobitaxuka.helper.PlayMusic;

import java.util.Random;

/**
 * Created by Ngoc_MiikoDesu on 11/2/2016.
 */

public class FreakingMath extends AppCompatActivity implements View.OnClickListener {

    private static final int MAX_QUESS = 15;
    private int score, currQues, stn1, stn2, result, res;
    private ImageView btnTrue, btnFalse;
    private TextView tvQuestion, tvScore, tvResult;
    private Random rd;
    private boolean check, choiseButton;
    private MyAsyncTask myAsyncTask;
    private Context myContext;
    private char operation;
    private ProgressBar pbTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freaking_math);

        control();

    }

    private void control() {
        btnTrue = (ImageView) findViewById(R.id.btnTrue);
        btnFalse = (ImageView) findViewById(R.id.btnFalse);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvResult = (TextView) findViewById(R.id.tvResult);
        tvScore = (TextView) findViewById(R.id.tvScore);
        pbTime = (ProgressBar) findViewById(R.id.pbTime);

        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);

        score = 0;
        currQues = 0;
        rd = new Random();
        choiseButton = false;
        myContext = this.getBaseContext();


        initQuess();

    }

    private void initQuess() {
        if (currQues == MAX_QUESS - 1) {
            finish();
            return;
        }
        currQues++;
        stn1 = random();
        stn2 = random();
        operation = randomChar();

        res = randomQuess(stn1, stn2, operation);

        setData();

        if (isTrue()) check = true;
        else check = false;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTrue: {
                choiseButton = true;
                sleepThread(10);
                if (check) {
                    PlayMusic.playMusic(this, "freaking_math_correct_answer");
                    score++;
                } else {
                    PlayMusic.playMusic(this, "freaking_math_wrong_answer");
                }
                initQuess();
                if (myAsyncTask != null)
                    myAsyncTask.cancel(true);
                myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
                break;
            }
            case R.id.btnFalse: {
                choiseButton = false;
                sleepThread(10);
                if (!check) {
                    PlayMusic.playMusic(this, "freaking_math_correct_answer");
                    score++;
                } else {
                    PlayMusic.playMusic(this, "freaking_math_wrong_answer");
                }
                initQuess();
                if (myAsyncTask != null)
                    myAsyncTask.cancel(true);
                myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
                break;
            }
        }
    }


    private boolean isTrue() {
        return res == result;
    }

    private void setData() {
        tvQuestion.setText(stn1 + " " + operation + " " + stn2);
        tvResult.setText(" = " + res);
        tvScore.setText(score + "=" + result);
    }

    private int random() {
        return rd.nextInt(20);
    }

    private int random(int m) {
        return rd.nextInt(m);
    }

    private char randomChar() {
        int tmp = rd.nextInt(2);
        switch (tmp) {
            case 0:
                return '+';
            case 1:
                return '-';
        }
        return ' ';
    }

    private int randomQuess(int STN1, int STN2, char operation) {
        int k;
        int tmp = rd.nextInt(2);
        switch (tmp) {
            case 0:
                k = 1;
                break;
            default:
                k = -1;
                break;
        }
        switch (operation) {
            case '+': {
                result = STN1 + STN2;
                return random(3) * k + result;
            }
            case '-': {
                result = STN1 - STN2;
                return random(3) * k + result;
            }

        }
        return -1;
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


    private class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            showToast("onPreExecute");
//            pbTime = (ProgressBar) findViewById(R.id.pbTime);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i <= 100; i++) {
                SystemClock.sleep(100);
                publishProgress(i);
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pbTime.setProgress(values[0]);
        }

        @Override
        protected void onCancelled() {
            showToast("onCancelled");
            super.onCancelled();
            pbTime.setProgress(0);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            showToast("onPostExecute");
            super.onPostExecute(aVoid);
            PlayMusic.playMusic(myContext, "freaking_math_wrong_answer");
            this.cancel(true);
            initQuess();

        }
    }

    private void sleepThread(int s) {
        if (choiseButton == false) {
            Glide.with(this).load(this.getResources().getIdentifier("freaking_math_false_choise", "drawable", getPackageName()))
                    .asBitmap()
                    .placeholder(R.drawable.freaking_math_false_choise)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(btnFalse);
        } else {
            Glide.with(this).load(this.getResources().getIdentifier("freaking_math_true_choise", "drawable", getPackageName()))
                    .asBitmap()
                    .placeholder(R.drawable.freaking_math_true_choise)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(btnTrue);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Glide.with(myContext).load(myContext.getResources().getIdentifier("freaking_math_false_button", "drawable", getPackageName()))
                        .asBitmap()
                        .placeholder(R.drawable.freaking_math_false_button)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(btnFalse);
                Glide.with(myContext).load(myContext.getResources().getIdentifier("freaking_math_true_button", "drawable", getPackageName()))
                        .asBitmap()
                        .placeholder(R.drawable.freaking_math_true_button)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(btnTrue);
            }
        }, s);
    }
}
