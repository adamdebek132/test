package com.example.menu_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class Quiz extends AppCompatActivity {

    MediaPlayer timer_sound;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;

    TextView textView;

    private TextView questionTextView;

    private Question[] questions = new Question[] {
            new Question(R.string.q_stolica, false),
            new Question(R.string.q_stolica_p,true),
            new Question(R.string.q_browswer,false),
            new Question(R.string.q_resources,false),
            new Question(R.string.q_finals,true)
    };

    private int currentIndex = 0;

    private void checkAnswerCorrectness (boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if (userAnswer == correctAnswer ){
            resultMessageId = R.string.correct_answer;
        } else {
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText( this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        timer_sound = MediaPlayer.create(Quiz.this, R.raw.timer);
        timer_sound.start();
        timer_sound.setLooping(true);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        //nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);
        questionTextView.setText(questions[currentIndex].getQuestionId());

        textView = findViewById(R.id.text_view);
        long duration = TimeUnit.SECONDS.toMillis(6);
        CountDownTimer timer;

        timer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                String sDuration = String.format(Locale.ENGLISH, "%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(l),
                        TimeUnit.MILLISECONDS.toSeconds(l) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                textView.setText(sDuration);
            }

            @Override
            public void onFinish() {
                currentIndex = (currentIndex + 1)%questions.length;
                setNextQuestion();
                this.start();
            }
        }.start();

        trueButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness( true);
                currentIndex = (currentIndex + 1)%questions.length;
                setNextQuestion();
                timer.start();

            }
        });

        falseButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness( false);
                currentIndex = (currentIndex + 1)%questions.length;
                setNextQuestion();
                timer.start();
            }
        });
/*
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1)%questions.length;
                setNextQuestion();
            }
        });
        setNextQuestion(); */

    }

    @Override
    protected void onPause() {
        super.onPause();
        timer_sound.stop();
    }


}