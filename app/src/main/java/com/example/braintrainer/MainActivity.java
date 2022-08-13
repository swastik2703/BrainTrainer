package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    TextView bannerText;
    int locationOfAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    TextView resultTextView;
    TextView ScoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView quesTextView;
    TextView TimerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        TimerTextView.setText("30s");
        ScoreTextView.setText(Integer.toString(score)+ "/"+ Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                //yaha hum timer ko update krte hai
                TimerTextView.setText(String.valueOf(millisUntilFinished/1000)+ "s");
            }

            @Override
            public void onFinish() {
                //when whole quiz is completed this method will run
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view){
       if(Integer.toString(locationOfAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct Answer");
            score++;    //agar sahi hua toh score update krdo
        }
       else{
           resultTextView.setText("Wrong:(");
       }
       numberOfQuestions++; //hr answer pe number of questions increase hote rhenge
       ScoreTextView.setText(Integer.toString(score)+ "/"+ Integer.toString(numberOfQuestions));    //score show kaise hoga aise
        newQuestion();
    }

    //making New function to display new Question everytime on the screen
    public  void newQuestion(){
        //****** Random ka use islye krenge kyuki isse within range koi bhi value display kra skta hai ********
        Random random = new Random();
        int a = random.nextInt(21); //a can range from 0 to 20
        int b = random.nextInt(21); //b can range from 0 to 20

        //now to display the random numbers in text view import sum Text view
        quesTextView.setText(Integer.toString(a)+ "+" + Integer.toString(b));
        locationOfAnswer = random.nextInt(4);   //since we have 4 questions

        //hame answers ko wipe out bhi krna hai agar galat ya sahi ho toh
        answer.clear();
        for(int i=0; i<4; i++){
            //4 option hai isliye 4 tk chlayenge
            //sabse phle 1 correct answer hoga or 3 incorrect toh phle random location decide kro
            if(i == locationOfAnswer){
                answer.add(a+b);
            }
            else{
                //since hmne range le hai 20 tk toh max sum 40 aa skta hai
                //ek random wrong answer bnao
                int wrongAns = random.nextInt(41);
                while(wrongAns == a+b){
                    //wrong ans ko update krdo
                    wrongAns = random.nextInt(41);
                }
                //add krdo wrong ans
                answer.add(wrongAns);
            }
        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }

    ArrayList<Integer> answer = new ArrayList<Integer>();

    public void Start(View view){
        goButton.setVisibility(View.INVISIBLE);
        bannerText.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.TimerTextView));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
        bannerText = findViewById(R.id.bannerText);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        quesTextView = findViewById(R.id.quesTextView);
        ScoreTextView = findViewById(R.id.ScoreTextView);
        resultTextView = findViewById(R.id.resultTextView);
        TimerTextView = findViewById(R.id.TimerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);
        goButton.setVisibility(View.VISIBLE);
        bannerText.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);





    }
}