package com.refreshmymemory.view_presenter.landing;

public interface LandingContract {
    interface View {
        void showTakeQuiz();
        void showAddCourse();
        void showMessages();
        void showGetStudentReport();
        void showManageAccount();
        void showLogout();
        void informUser(String message);
    }

    interface Presenter {

    }
}
