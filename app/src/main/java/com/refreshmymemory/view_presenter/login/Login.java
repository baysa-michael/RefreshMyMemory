package com.refreshmymemory.view_presenter.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.refreshmymemory.R;
import com.refreshmymemory.view_presenter.landing.Landing;

public class Login extends AppCompatActivity implements LoginContract.View {
    private final static String TAG = "Login";
    private LoginPresenter presenter;
    private ProgressBar indeterminateProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set Buttons
        Button loginButton = findViewById(R.id.loginLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogin();
            }
        });

        Button cancelButton = findViewById(R.id.loginCancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCancel();
            }
        });

        // Initialize Presenter
        presenter = new LoginPresenter();

        // Set Indeterminate Progress Bar to Gone
        indeterminateProgressBar = findViewById(R.id.createaccountIndeterminateProgress);
        indeterminateProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLogin() {
        // Start Indeterminate Progress Bar
        indeterminateProgressBar.setVisibility(View.VISIBLE);

        // Gather information from form
        EditText usernameEdit = findViewById(R.id.loginUsernameEdit);
        EditText passwordEdit = findViewById(R.id.loginPasswordEdit);
        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        // Check to make sure information has been filled out
        if (username.isEmpty() || password.isEmpty()) {
            // Stop Indeterminate Progress Bar
            indeterminateProgressBar.setVisibility(View.GONE);

            informUser("ERROR:  Not all fields have been completed");
            return;
        }

        // Send request to create new user and return result
        if (presenter.login(username, password)) {
            // Stop Indeterminate Progress Bar
            indeterminateProgressBar.setVisibility(View.GONE);

            informUser("Successfully Logged In to User Account");

            // Clear User Input
            usernameEdit.getText().clear();
            passwordEdit.getText().clear();

            // Move to Landing If Successful
            Intent newIntent = new Intent(this, Landing.class);
            startActivity(newIntent);
        } else {
            // Stop Indeterminate Progress Bar
            indeterminateProgressBar.setVisibility(View.GONE);

            informUser("ERROR:  Invalid Username/Password Combination");
        }
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
