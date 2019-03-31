package com.refreshmymemory.view_presenter.welcome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.refreshmymemory.R;

public class Welcome extends AppCompatActivity implements WelcomeContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void showCreateAccount() {

    }

    @Override
    public void showLogin() {

    }

}
