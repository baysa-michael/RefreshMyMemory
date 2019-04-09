package com.refreshmymemory.model;

import java.util.List;

public class MultipleChoiceQuestion implements QuestionInterface {
    private String question;
    private List<String> answerList;
    private int correctAnswer;
    private int userAnswer;

    public MultipleChoiceQuestion(String question, List<String> answerList,
                                  int correctAnswer) {
        this.question = question;
        this.answerList = answerList;
        this.correctAnswer = correctAnswer;
        this.userAnswer = -1;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public int gradeQuestion() {
        return (userAnswer == correctAnswer ? 1 : 0);
    }
}
