package com.refreshmymemory.view_presenter.select_quiz;

import java.util.Set;

public interface SelectQuizContract {
    interface View {
        void startQuiz(String targetCourse);
        void showCancel();
        void informUser(String message);
    }

    interface Presenter {
        Set<String> getUserCourseList();
    }
}
