package com.refreshmymemory.view_presenter.login;

public interface LoginContract {
    interface View {
        void showLogin();
        void showCancel();
        void informUser(String message);
    }

    interface Presenter {
        int obtainSalt(String username);
        boolean login(String username, String password);
    }
}
