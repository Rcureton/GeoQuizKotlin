package com.bignerdranch.android.geoquiz;

public class QuestionSample {

    private int mText;
    private boolean mAnswer;

    public QuestionSample(int id, boolean answer) {
        mAnswer = answer;
        mText = id;
    }

    public int getText() {
        return mText;
    }

    public void setText(int text) {
        mText = text;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
