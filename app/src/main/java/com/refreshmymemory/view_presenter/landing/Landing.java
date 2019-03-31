package com.refreshmymemory.view_presenter.landing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.refreshmymemory.R;

public class Landing extends AppCompatActivity implements LandingContract.View {
    private final static String TAG = "Landing";
    private LandingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        // Initialize Presenter
        presenter = new LandingPresenter();
    }

    @Override
    public void showTakeQuiz(){

    }

    @Override
    public void showManageAccount(){

    }

    @Override
    public void showMessages(){

    }

    @Override
    public void showLogout(){

    }
}
