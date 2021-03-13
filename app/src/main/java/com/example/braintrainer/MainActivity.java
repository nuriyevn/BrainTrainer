package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>(); // potentional answers
    int locationOfCorrectAnswer;
    int incorrectAnswer;
    int score = 0;

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    int numberOfQuestions = 0;
    private TextView timerTextView;
    private Button playAgainButton;


    public void playAgain(View view)
    {
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("New game!");
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");

        generateQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Long.toString(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);

        generateQuestion();

        playAgain(findViewById(R.id.playAgainButton)); // simulation button press
    }

    public void generateQuestion() {
        answers.clear();

        Random rand = new Random();

        int a = rand.nextInt(21); // [0 : 20]  // 15
        int b = rand.nextInt(21); // [0 : 20] // 5

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4); // [0-3]  // 2

        // [rand, rand, correct, rand]
        for (int i = 0; i < 4; i++)
        {
            if (i == locationOfCorrectAnswer)
            {
                answers.add(a + b);
            }
            else // generating random Incorrect answer s
            {
                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) // making sure that incorrect is incorrect
                {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer); // 20
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);

    }

    public void chooseAnswer(View view){
        Log.i("Brain", (String) view.getTag());


        //tag == locationOfCorrectAnswer
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            score++;
            resultTextView.setText("Correct!");
        }
        else
        {
            resultTextView.setText("Wrong!");
        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        generateQuestion();

    }
}