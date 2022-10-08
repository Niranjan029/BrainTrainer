package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {
     TextView timerview ,status;
     TextView scoreview;
     TextView questionview ;
     Button button0,button1,button2,button3,resetbutton ;
    int locationofcorrectanswer ;
    ArrayList<Integer> questions = new ArrayList<Integer>();
    int index = 0 ,countcorrect=0;
     public void onTap(View view)
     {

          Button btn = (Button) view ;

         scoreview.setText(countcorrect + "/" + index);
          int tappedbutton = Integer.parseInt(btn.getTag().toString());

              if (tappedbutton == locationofcorrectanswer ) {

                  status.setText("Correct");
                  countcorrect++;
                 // questionview.setText(questions.get(index));
              } else {
                  status.setText("Wrong");
                 // questionview.setText(questions.get(index));
              }
                index++;
         scoreview.setText(countcorrect + "/" + index);
                 newQuestion();
     }
     public void newQuestion()
     {
         int a , b ;
         Random rand = new Random();
         a=rand.nextInt(21);
         b=rand.nextInt(21);
         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
         button0.setClickable(true);
         button1.setClickable(true);
         button2.setClickable(true);
         button3.setClickable(true);
         questionview.setText(Integer.toString(a) +" + " + Integer.toString(b));
         locationofcorrectanswer = rand.nextInt(4);
       questions.clear();
         for(int i=0 ;i<4;i++)
         {
             if(i==locationofcorrectanswer)
             {
                 questions.add(a+b);
             }
             else{
                 int wronganswer= rand.nextInt(41);
                 while(wronganswer == a+b)
                 {
                     wronganswer = rand.nextInt(41);
                 }
                 questions.add(wronganswer) ;
             }
         }
         button0.setText(Integer.toString(questions.get(0)));
         button1.setText(Integer.toString(questions.get(1)));
         button2.setText(Integer.toString(questions.get(2)));
         button3.setText(Integer.toString(questions.get(3)));
     }
public  void playagain(View view)
{
    index=0;countcorrect=0;
    scoreview.setText(countcorrect + "/" + index);
     resetbutton.setVisibility(View.INVISIBLE);
    newQuestion();
    new CountDownTimer(30000,1000)
    {
        @Override

        public void onTick(long l) {
            timerview.setText(l/1000+"s");
        }

        @Override
        public void onFinish() {
            status.setText("Time up ");
            scoreview.setText(countcorrect + "/" + index);
            resetbutton.setVisibility(View.VISIBLE);
            button0.setClickable(false);
            button1.setClickable(false);
            button2.setClickable(false);
            button3.setClickable(false);
        }

    }.start();
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        timerview = findViewById(R.id.timer);
        resetbutton = findViewById(R.id.resetbutton);
        questionview =  findViewById(R.id.problems);
        scoreview = findViewById(R.id.score);
        status =findViewById(R.id.status);
        playagain(findViewById(R.id.status));
        
    }
}