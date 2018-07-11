package com.sws.android.quizactivity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE="com.sws.android.quizactivity.answer_is_true";
    private static final String EXTRA_ANSWER_SHOW="com.sws.android.quizactivity.answer_show";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        mAnswerTextView=(TextView)findViewById(R.id.answer_text_view);
        mShowAnswerButton=(Button)findViewById(R.id.show_answer_button);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }else{
                    mAnswerTextView.setText(R.string.false_button);
                }

                setAnswerShowResult(true);

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    int cx=mShowAnswerButton.getWidth()/2;
                    int cy=mShowAnswerButton.getHeight()/2;
                    float radius=mShowAnswerButton.getWidth();
                    Animator anim= ViewAnimationUtils.createCircularReveal(mShowAnswerButton,cx,cy,radius,0);
                    anim.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            mShowAnswerButton.setVisibility(View.INVISIBLE);
//                        if(mAnswerIsTrue){
//                            mAnswerTextView.setText(R.string.true_button);
//                        }else{
//                            mAnswerTextView.setText(R.string.false_button);
//                        }
//
//                        setAnswerShowResult(true);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    anim.start();
                }else{
                    mShowAnswerButton.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public static Intent newIntent(Context packageContext,boolean answerIsTrue){
        Intent intent=new Intent(packageContext,CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return intent;
    }

    private void setAnswerShowResult(boolean isAnswerShow){
        Intent intent=new Intent();
        intent.putExtra(EXTRA_ANSWER_SHOW,isAnswerShow);
        setResult(RESULT_OK,intent);
    }

    public static boolean wasAnswerShow(Intent data){
        return data.getBooleanExtra(EXTRA_ANSWER_SHOW,false);
    }
}
