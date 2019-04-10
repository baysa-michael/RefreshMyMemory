package com.refreshmymemory.view_presenter.quiz;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.refreshmymemory.R;
import com.refreshmymemory.model.MultipleChoiceQuestion;
import com.refreshmymemory.model.QuestionInterface;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends AppCompatActivity implements QuizContract.View,
        MCQuizQuestionFragment.OnFragmentInteractionListener {
    private static final String TAG = "Quiz***";
    private QuizPresenter presenter;
    private List<QuestionInterface> quiz;
    private FragmentManager manager;
    private List<MCQuizQuestionFragment> quizFragments;
    private int currentQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Set Buttons
        Button previousButton = findViewById(R.id.quizPreviousQuestionButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPrevious();
            }
        });

        Button nextButton = findViewById(R.id.quizNextQuestionButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNext();
            }
        });

        Button submitQuizButton = findViewById(R.id.quizSubmitButton);
        submitQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSubmitQuiz();
            }
        });

        // Set up presenter
        presenter = new QuizPresenter();

        // Retrieve Quiz Based on Intent in List format
        quiz = new ArrayList<>(presenter.constructQuiz(getIntent().getStringExtra("course")));

        // Set question index for each question
        for (int i = 0; i < quiz.size(); i++) {
            ((MultipleChoiceQuestion) quiz.get(i)).setQuestionIndex(i);
        }

        // Initialize Fragment Manager
        manager = getSupportFragmentManager();

        // Initialize Fragments
        quizFragments = new ArrayList<>();
        for (QuestionInterface inputQuestion : quiz) {
            quizFragments.add(MCQuizQuestionFragment
                    .newInstance((MultipleChoiceQuestion) inputQuestion));
        }

        // Set Initial Position
        currentQuestion = 0;

        // Attach Initial Fragment
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.quizQuestionLayout, quizFragments.get(currentQuestion));
        transaction.commit();
    }

    @Override
    public void showPrevious() {
        if (currentQuestion > 0) {
            // Decrement Current Question
            currentQuestion -= 1;

            // Update Fragment
            updateFragment(currentQuestion);
        }
    }

    @Override
    public void showNext() {
        if (currentQuestion < quiz.size() - 1) {
            // Decrement Current Question
            currentQuestion += 1;

            // Update Fragment
            updateFragment(currentQuestion);
        }
    }

    @Override
    public void showSubmitQuiz() {
        // Grade Quiz
        int score = presenter.gradeQuiz(quiz);

        informUser("Quiz Results:  " + score + "/" + quiz.size());

        // ADD LOGIC TO SEND RESULTS TO SERVER ****************************************
        finish();
    }

    @Override
    public void onAnswerSelection(int questionIndex, int responseIndex) {
        // Update the question response
        ((MultipleChoiceQuestion) quiz.get(questionIndex)).setUserAnswer(responseIndex);
    }

    @Override
    public void onChangeFragment(int questionIndex) {
        // Update the fragment being replaced
        MultipleChoiceQuestion targetQuestion = (MultipleChoiceQuestion) quiz.get(questionIndex);
        quizFragments.set(questionIndex, MCQuizQuestionFragment
                .newInstance(targetQuestion));
    }

    public void updateFragment(int questionNumber) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.quizQuestionLayout, quizFragments.get(questionNumber));
        transaction.commit();
    }

    @Override
    public void informUser(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(this, message, duration);
        toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
        toastSuccessful.show();
    }
}
