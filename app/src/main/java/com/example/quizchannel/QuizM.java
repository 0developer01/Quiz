package com.example.quizchannel;

public class QuizM {

    private String quizId;
    private String quizName;
    private boolean visible;
    private long duration;

    public QuizM() {
    }

    public QuizM(String quizId, String quizName, boolean visible, long duration) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.visible = visible;
        this.duration = duration;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

}
