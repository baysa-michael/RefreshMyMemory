package com.refreshmymemory.view_presenter.quiz;

import com.refreshmymemory.model.QuestionInterface;

import java.util.Set;

public interface QuizContract {
    interface View {
        void showCancel();
    }

    interface Presenter {
        Set<QuestionInterface> constructQuiz(String course);
    }
}
