package com.refreshmymemory.view_presenter.quiz;

import com.refreshmymemory.model.QuestionInterface;

import java.util.List;
import java.util.Set;

public interface QuizContract {
    interface View {
        void showPrevious();
        void showNext();
        void showSubmitQuiz();
        void informUser(String message);
    }

    interface Presenter {
        Set<QuestionInterface> constructQuiz(String course);
        int gradeQuiz(List<QuestionInterface> quiz);
    }
}
