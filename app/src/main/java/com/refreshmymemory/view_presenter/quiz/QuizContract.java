package com.refreshmymemory.view_presenter.quiz;

public interface QuizContract {
    interface View {
        void showTestQuiz();
        void showCancel();
    }

    interface Presenter {
        void handleTestQuiz();
        void handleClickCancel();
    }
}
