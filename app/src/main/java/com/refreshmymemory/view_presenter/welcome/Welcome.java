package com.refreshmymemory.view_presenter.welcome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.refreshmymemory.R;

public class Welcome extends AppCompatActivity implements WelcomeContract.View {
    private final static String TAG = "Welcome";
    private WelcomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Initialize Presenter
        presenter = new WelcomePresenter();
    }

    @Override
    public void showCreateAccount() {

    }

    @Override
    public void showLogin() {

    }

}
