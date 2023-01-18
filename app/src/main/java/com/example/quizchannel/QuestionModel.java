package com.example.quizchannel;

public class QuestionModel {
    private String questionId;
    private String quizId;
    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String correctOne;
    private boolean visible;

    public QuestionModel() {
    }

    public QuestionModel(String questionId, String quizId, String question,
                         String choice1, String choice2, String choice3, String choice4,
                         String correctOne, boolean visible) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.correctOne = correctOne;
        this.visible = visible;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public String getCorrectOne() {
        return correctOne;
    }

    public void setCorrectOne(String correctOne) {
        this.correctOne = correctOne;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
