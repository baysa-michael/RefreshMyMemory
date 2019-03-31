package com.refreshmymemory.view_presenter.create_account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.refreshmymemory.R;

public class CreateAccount extends AppCompatActivity implements CreateAccountContract.View {
    private final static String TAG = "CreateAccount";
    private CreateAccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Initialize Presenter
        presenter = new CreateAccountPresenter();
    }

    @Override
    public void showConfirm() {

    }

    @Override
    public void showCancel() {

    }

}
