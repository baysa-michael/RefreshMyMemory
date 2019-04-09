package com.refreshmymemory.view_presenter.quiz;

import com.refreshmymemory.model.MultipleChoiceQuestion;
import com.refreshmymemory.model.QuestionInterface;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class QuizPresenter implements QuizContract.Presenter {
    @Override
    public Set<QuestionInterface> constructQuiz(String course) {
        Set<QuestionInterface> quiz = new LinkedHashSet<>();


        // Add Logic  to get quiz questions from servlet
        // *************** TEMPORARY *******************
        List<String> answers = new ArrayList<>();
        answers.add("To have fun");
        answers.add("To get rich");
        answers.add("To gain experience and prepare to live with Heavenly Father");
        answers.add("There is no meaning to life");
        quiz.add(new MultipleChoiceQuestion("What is the meaning of life?",
                answers,
                2));

        answers.clear();
        answers.add("John 3:16");
        answers.add("Matthew 8:12");
        answers.add("1 Nephi 3:7");
        answers.add("John 14:15");
        quiz.add(new MultipleChoiceQuestion("Reference of 'If ye love me, keep my commandments;",
                answers, 3));

        answers.clear();
        answers.add("Michael");
        answers.add("Noah");
        answers.add("Gabriel");
        answers.add("Jeremiah");
        quiz.add(new MultipleChoiceQuestion("What was Adam's pre-mortal name?",
                answers, 0));


        return quiz;
    }
}
