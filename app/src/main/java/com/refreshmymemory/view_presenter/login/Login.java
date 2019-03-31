package com.refreshmymemory.view_presenter.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.refreshmymemory.R;

public class Login extends AppCompatActivity implements LoginContract.View {
    private final static String TAG = "Login";
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Presenter
        presenter = new LoginPresenter();
    }

    @Override
    public void showLogin() {

    }

    @Override
    public void showCancel() {

    }

}
