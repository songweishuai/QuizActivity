package com.sws.android.quizactivity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private String TAG=QuizActivity.class.getSimpleName();
    private Button mTrueBurron;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;

    private int mCurrentIndex=0;
    private Question[] mQuestionBank={
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

       mTrueBurron=(Button)findViewById(R.id.true_button);
       mFalseButton=(Button)findViewById(R.id.false_button);

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Toast.makeText(QuizActivity.this,"true",Toast.LENGTH_SHORT).show();
                //Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
                checkAnswer(false);
            }
        });

        mTrueBurron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(QuizActivity.this,"true",Toast.LENGTH_SHORT).show();
                //Toast.makeText(QuizActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT).show();
                checkAnswer(true);
            }
        });

        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        int question=mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        mNextButton=(Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                int question=mQuestionBank[mCurrentIndex].getTextResId();
                mQuestionTextView.setText(question);
            }
        });

        mCheatButton=(Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: "+"Start Activity");
                //Intent intent=new Intent(QuizActivity.this,CheatActivity.class);
                Intent intent=CheatActivity.newIntent(QuizActivity.this,mQuestionBank[mCurrentIndex].isAnswerTrue());
                startActivity(intent);
                //startActivityForResult();
            }
        });
    }

    private void checkAnswer(boolean userPressedTrue){
        //Log.d(TAG, "checkAnswer: exception",new Exception());
        boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId=0;
        if(userPressedTrue==answerIsTrue){
            messageResId=R.string.correct_toast;
        }else{
            messageResId=R.string.incorrect_toast;
        }
        Toast.makeText(QuizActivity.this,messageResId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
