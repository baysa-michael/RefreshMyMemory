package com.refreshmymemory.model;

import java.util.List;

public class MultipleChoiceQuestion {
    private String question;
    private List<String> answerList;
    private int correctAnswerIndex;
    private int userAnswer;

    public MultipleChoiceQuestion(String question, List<String> answerList, int correctAnswerIndex) {
        this.question = question;
        this.answerList = answerList;
        this.correctAnswerIndex = correctAnswerIndex;
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

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }
}
