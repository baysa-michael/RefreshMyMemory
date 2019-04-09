package com.refreshmymemory.view_presenter.select_quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.refreshmymemory.R;

import java.util.Set;
import java.util.TreeSet;

public class SelectQuiz extends AppCompatActivity implements SelectQuizContract.View {
    private final static String TAG = "SelectQuiz";
    private SelectQuizPresenter presenter;
    private RecyclerView.Adapter myAdapter;
    private Set<String> courses;
    private ProgressBar indeterminateProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_quiz);

        // Initialize Presenter
        presenter = new SelectQuizPresenter();

        // Initialize the Courses Set
        courses = presenter.getUserCourseList();

        // Set Buttons
        Button logoutButton = findViewById(R.id.selectquizCancelButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCancel();
            }
        });

        // Set Recycler View w/ Layout Manager
        RecyclerView myRecycler = findViewById(R.id.selectquizRecyclerView);
        myRecycler.setHasFixedSize(true);
        myRecycler.addItemDecoration(new DividerItemDecoration(myRecycler.getContext(),
                DividerItemDecoration.VERTICAL));
        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(myLayoutManager);
        myAdapter = new SelectQuizRecyclerViewAdapter(this, courses,
                new SelectQuizRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String targetCourse) {
                startQuiz(targetCourse);
            }
        });
        myRecycler.setAdapter(myAdapter);

        // Set Indeterminate Progress Bar to Gone
        indeterminateProgressBar = findViewById(R.id.selectquizIndeterminateProgress);
        indeterminateProgressBar.setVisibility(View.GONE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Load User Course List
        courses = presenter.getUserCourseList();

        // Notify the adapter
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void startQuiz(String targetCourse) {
        // Start Indeterminate Progress Bar
        indeterminateProgressBar.setVisibility(View.VISIBLE);


        informUser("Starting Quiz for " + targetCourse);

        // Stop Indeterminate Progress Bar
        indeterminateProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showCancel() {
        finish();
    }

    @Override
    public void informUser(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(this, message, duration);
        toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
        toastSuccessful.show();
    }
}
