package com.refreshmymemory.view_presenter.create_account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.refreshmymemory.R;
import com.refreshmymemory.control.ServletConnection;

import java.util.Map;

public class CreateAccount extends AppCompatActivity implements CreateAccountContract.View {
    private final static String TAG = "CreateAccount";
    private CreateAccountPresenter presenter;
    private ProgressBar indeterminateProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Set Buttons
        Button confirmButton = findViewById(R.id.createaccountConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirm();
            }
        });

        Button cancelButton = findViewById(R.id.createaccountCancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCancel();
            }
        });

        // Initialize Presenter
        presenter = new CreateAccountPresenter();

        // Set Indeterminate Progress Bar to Gone
        indeterminateProgressBar = findViewById(R.id.createaccountIndeterminateProgress);
        indeterminateProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showConfirm() {
        // Start Indeterminate Progress Bar
        indeterminateProgressBar.setVisibility(View.VISIBLE);

        // Identify User Inputs
        // Gather information from form
        EditText usernameEdit = findViewById(R.id.createaccountUsernameEdit);
        EditText passwordEdit = findViewById(R.id.createaccountPasswordEdit);
        EditText displayNameEdit = findViewById(R.id.createaccountDisplayNameEdit);
        EditText emailEdit = findViewById(R.id.createaccountEmailEdit);

        // Gather information from form
        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String displayName = displayNameEdit.getText().toString();
        String email = emailEdit.getText().toString();

        // Check to make sure information has been filled out
        if (username.isEmpty() || password.isEmpty() || displayName.isEmpty() || email.isEmpty()) {
            // Stop Indeterminate Progress Bar
            indeterminateProgressBar.setVisibility(View.GONE);

            informUser("ERROR:  Not all fields have been completed");
            return;
        }

        // Check password
        if (!presenter.validatePasswordRequirements(password)) {
            // Stop Indeterminate Progress Bar
            indeterminateProgressBar.setVisibility(View.GONE);

            informUser("ERROR:  Password does not meet requirements");
            return;
        }

        // Prepare Create Account Request
        Map<String, String> requestData =
                presenter.prepareCreateAccountRequest(username, password, email, displayName);

        // Run the AsyncTask request to server if there is valid data
        if (requestData != null) {
            // Launch Connection
            try {
                // Set URL
                String url = "localhost";  //************* UPDATE URL *************************

                ServletConnection newConnection = new ServletConnection(requestData);

                newConnection.execute(url);
            } catch (Exception e) {
                // Stop Indeterminate Progress Bar
                indeterminateProgressBar.setVisibility(View.GONE);

                informUser("ERROR:  Unable to connect to server");

                e.printStackTrace();
            }



            // Stop Indeterminate Progress Bar
            indeterminateProgressBar.setVisibility(View.GONE);

            informUser("Successfully Added User Account");

            // Clear User Input
            usernameEdit.getText().clear();
            passwordEdit.getText().clear();
            displayNameEdit.getText().clear();
            emailEdit.getText().clear();

            // Finish the activity if successful
            finish();
        } else {
            // Stop Indeterminate Progress Bar
            indeterminateProgressBar.setVisibility(View.GONE);

            informUser("ERROR:  Unable to create new account");
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
