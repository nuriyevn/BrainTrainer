package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
 
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answers; /// potentional answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        TextView sumTextView = (TextView)findViewById(R.id.sumTextView);

        Random rand = new Random();

        int a = rand.nextInt(21); // [0 : 20]
        int b = rand.nextInt(21); // [0 : 20]

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));


    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);

    }

    public void chooseAnswer(View view){

    }
}