package com.refreshmymemory.view_presenter.quiz;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.refreshmymemory.R;

public class Quiz extends AppCompatActivity implements QuizContract.View,
        MCQuizQuestionFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    @Override
    public void showCancel() {
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
