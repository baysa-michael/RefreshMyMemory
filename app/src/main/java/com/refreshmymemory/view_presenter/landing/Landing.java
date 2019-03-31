package com.refreshmymemory.view_presenter.landing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.refreshmymemory.R;

public class Landing extends AppCompatActivity implements LandingContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
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
