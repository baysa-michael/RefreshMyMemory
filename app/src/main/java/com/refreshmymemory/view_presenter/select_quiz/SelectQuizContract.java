package com.refreshmymemory.view_presenter.select_quiz;

public interface SelectQuizContract {
    interface View {
        void showTestQuiz();
        void showCancel();
    }

    interface Presenter {
        void handleTestQuiz();
        void handleClickCancel();
    }
}
