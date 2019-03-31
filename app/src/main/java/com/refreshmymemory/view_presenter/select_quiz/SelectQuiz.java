package com.refreshmymemory.view_presenter.select_quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.refreshmymemory.R;

public class SelectQuiz extends AppCompatActivity implements SelectQuizContract.View {
    private final static String TAG = "SelectQuiz";
    private SelectQuizPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_quiz);

        // Initialize Presenter
        presenter = new SelectQuizPresenter();
    }

    @Override
    public void showTestQuiz() {

    }

    @Override
    public void showCancel() {

    }
}
