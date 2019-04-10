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
        List<String> answers1 = new ArrayList<>();
        answers1.add("To have fun");
        answers1.add("To get rich");
        answers1.add("To gain experience and prepare to live with Heavenly Father");
        answers1.add("There is no meaning to life");
        quiz.add(new MultipleChoiceQuestion("What is the meaning of life?",
                answers1,
                2));

        List<String> answers2 = new ArrayList<>();
        answers2.add("John 3:16");
        answers2.add("Matthew 8:12");
        answers2.add("1 Nephi 3:7");
        answers2.add("John 14:15");
        quiz.add(new MultipleChoiceQuestion("Reference of 'If ye love me, keep my commandments;",
                answers2, 3));

        List<String> answers3 = new ArrayList<>();
        answers3.clear();
        answers3.add("Michael");
        answers3.add("Noah");
        answers3.add("Gabriel");
        answers3.add("Jeremiah");
        quiz.add(new MultipleChoiceQuestion("What was Adam's pre-mortal name?",
                answers3, 0));

        return quiz;
    }

    @Override
    public int gradeQuiz(List<QuestionInterface> quiz) {
        int score = 0;
        for (QuestionInterface question : quiz) {
            score += question.gradeQuestion();
        }

        return score;
    }
}
