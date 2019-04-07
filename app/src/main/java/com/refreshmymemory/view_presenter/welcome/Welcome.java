package com.refreshmymemory.view_presenter.welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.refreshmymemory.R;
import com.refreshmymemory.view_presenter.create_account.CreateAccount;
import com.refreshmymemory.view_presenter.login.Login;

public class Welcome extends AppCompatActivity implements WelcomeContract.View {
    private final static String TAG = "Welcome";
    private WelcomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Set Buttons
        Button loginButton = findViewById(R.id.welcomeLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogin();
            }
        });

        Button createNewAccountButton = findViewById(R.id.welcomeCreateAccountButton);
        createNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateAccount();
            }
        });

        // Initialize Presenter
        presenter = new WelcomePresenter();
    }

    @Override
    public void showCreateAccount() {
        Intent newIntent = new Intent(this, CreateAccount.class);
        startActivity(newIntent);
    }

    @Override
    public void showLogin() {
        Intent newIntent = new Intent(this, Login.class);
        startActivity(newIntent);
    }
}
