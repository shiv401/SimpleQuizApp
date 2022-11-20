package com.shiv.easyquizapp;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class MainActivity2 extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//    }
//}


import static android.widget.Toast.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionNumber;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score = 0;
    int chapter=1;
    int numberOfQuiz=3;
    int totalQuestion = QuestionAnswer1.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        totalQuestionsTextView = findViewById(R.id.total_question);
        //questionNumber = findViewById(R.id.qno);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : " + totalQuestion);

        loadNewQuestion();


    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.submit_btn) {
            if(selectedAnswer.equals(""))
            {
//                Toast.makeText(getBaseContext(),
//                                "This a toast message",
//                                Toast.LENGTH_LONG)
//                        .show();
                new AlertDialog.Builder(this)
                       // .setTitle(passStatus)
                        .setMessage("Please select one option...")
                        //.setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                        // .setPositiveButton("NextQUIZ",(dialogInterface, i) -> nextQuiz() )
                        .setCancelable(true)
                        .show();
                    return;
            }
           if(chapter==1) {
               if (selectedAnswer.equals(QuestionAnswer1.correctAnswers[currentQuestionIndex])) {
                   score++;
               }
           }
           if(chapter==2) {
               if (selectedAnswer.equals(QuestionAnswer2.correctAnswers[currentQuestionIndex])) {
                   score++;
               }
           }
            if(chapter==3) {
                if (selectedAnswer.equals(QuestionAnswer3.correctAnswers[currentQuestionIndex])) {
                    score++;
                }
            }

               currentQuestionIndex++;
               selectedAnswer="";
               loadNewQuestion();

           } else {
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }

    }

    void loadNewQuestion() {

        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
     if(chapter==1) {
         questionTextView.setText(QuestionAnswer1.question[currentQuestionIndex]);
        // questionNumber.setText("Q.No. -> "+(currentQuestionIndex+1));
         ansA.setText(QuestionAnswer1.choices[currentQuestionIndex][0]);
         ansB.setText(QuestionAnswer1.choices[currentQuestionIndex][1]);
         ansC.setText(QuestionAnswer1.choices[currentQuestionIndex][2]);
         ansD.setText(QuestionAnswer1.choices[currentQuestionIndex][3]);
     }
     if(chapter==2)
     {
         questionTextView.setText(QuestionAnswer2.question[currentQuestionIndex]);
        // questionNumber.setText("Q.No. -> "+(currentQuestionIndex+1));
         ansA.setText(QuestionAnswer2.choices[currentQuestionIndex][0]);
         ansB.setText(QuestionAnswer2.choices[currentQuestionIndex][1]);
         ansC.setText(QuestionAnswer2.choices[currentQuestionIndex][2]);
         ansD.setText(QuestionAnswer2.choices[currentQuestionIndex][3]);
     }
        if(chapter==3)
        {
            questionTextView.setText(QuestionAnswer3.question[currentQuestionIndex]);
           // questionNumber.setText("Q.No. -> "+(currentQuestionIndex+1));
            ansA.setText(QuestionAnswer3.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer3.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer3.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer3.choices[currentQuestionIndex][3]);
        }
//        if(chapter==4)
//        {
//            questionTextView.setText(QuestionAnswer2.question[currentQuestionIndex]);
//            questionNumber.setText("Q.No. -> "+(currentQuestionIndex+1));
//            ansA.setText(QuestionAnswer2.choices[currentQuestionIndex][0]);
//            ansB.setText(QuestionAnswer2.choices[currentQuestionIndex][1]);
//            ansC.setText(QuestionAnswer2.choices[currentQuestionIndex][2]);
//            ansD.setText(QuestionAnswer2.choices[currentQuestionIndex][3]);
//        }
    }

    void finishQuiz() {
        String passStatus = "";
        if (score > totalQuestion * 0.60) {
            passStatus = "Passed";
            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("Score is " + score + " out of " + totalQuestion)
                    //.setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                    .setPositiveButton("NextQUIZ", (dialogInterface, i) -> nextQuiz())
                    .setCancelable(false)
                    .show();


        } else {
            passStatus = "Failed";
            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("Score is " + score + " out of " + totalQuestion)
                    .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                    // .setPositiveButton("NextQUIZ",(dialogInterface, i) -> nextQuiz() )
                    .setCancelable(false)
                    .show();


        }

    }

    private void restartQuiz() {
        score = 0;
        chapter=chapter;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }


    private void nextQuiz() {
        currentQuestionIndex=0;
        score = 0;
        chapter=chapter+1;
        if(chapter<=numberOfQuiz)
           loadNewQuestion();
        else
            new AlertDialog.Builder(this)
                    .setTitle("HURRAY!! \nYou Have Completed All Quiz :)")
                    .setMessage("You are having good Knowledge.For more such quizes please give review and feedback.\nSee you again :)")
                    .setPositiveButton("Start Again", (dialogInterface, i) -> restartQuizAgain())
                    // .setPositiveButton("NextQUIZ",(dialogInterface, i) -> nextQuiz() )
                    .setNegativeButton("EXIT",(dialogInterface, i) -> exit())
                    .setCancelable(false)
                    .show();

    }

    private void exit() {
        //Process.killProcess(Process.myPid());
        //super.onDestroy();
        finish();
    }

    private void restartQuizAgain() {
        score = 0;
        chapter=1;
        currentQuestionIndex = 0;
        loadNewQuestion();

    }
}