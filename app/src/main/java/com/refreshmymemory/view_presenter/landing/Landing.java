package com.refreshmymemory.view_presenter.landing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.refreshmymemory.R;
import com.refreshmymemory.view_presenter.select_quiz.SelectQuiz;

import java.util.Set;

public class Landing extends AppCompatActivity implements LandingContract.View {
    private final static String TAG = "Landing";
    private LandingPresenter presenter;
    private RecyclerView.Adapter myAdapter;
    private ProgressBar indeterminateProgressBar;
    private Set<String> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        // Set Buttons
        Button takeQuizButton = findViewById(R.id.landingTakeQuizButton);
        takeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTakeQuiz();
            }
        });

        Button addCourseButton = findViewById(R.id.landingAddCourseButton);
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddCourse();
            }
        });

        Button messagesButton = findViewById(R.id.landingMessagesButton);
        messagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessages();
            }
        });

        Button getStudentReportButton = findViewById(R.id.landingGetStudentReportButton);
        getStudentReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGetStudentReport();
            }
        });

        Button manageAccountButton = findViewById(R.id.landingManageAccountButton);
        manageAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showManageAccount();
            }
        });

        Button logoutButton = findViewById(R.id.landingLogoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogout();
            }
        });

        // Initialize Presenter
        presenter = new LandingPresenter();
    }

    @Override
    public void showTakeQuiz() {
        // Move to Select Quiz Screen
        Intent newIntent = new Intent(this, SelectQuiz.class);
        startActivity(newIntent);
    }

    @Override
    public void showAddCourse() {
        informUser("NOTE:  Feature Not Yet Added");
    }

    @Override
    public void showMessages() {
        informUser("NOTE:  Feature Not Yet Added");
    }

    @Override
    public void showGetStudentReport() {
        informUser("NOTE:  Feature Not Yet Added");
    }

    @Override
    public void showManageAccount() {
        informUser("NOTE:  Feature Not Yet Added");
    }

    @Override
    public void showLogout() {
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