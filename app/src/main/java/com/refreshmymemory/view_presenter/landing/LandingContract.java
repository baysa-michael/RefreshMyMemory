package com.refreshmymemory.view_presenter.landing;

public interface LandingContract {
    interface View {
        void showTakeQuiz();
        void showManageAccount();
        void showMessages();
        void showLogout();
    }

    interface Presenter {
        void handleTakeQuiz();
        void handleManageAccount();
        void handleMessages();
        void handleLogout();
    }
}
