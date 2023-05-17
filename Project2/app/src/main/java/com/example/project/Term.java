package com.example.project;

public class Term {

    private String riddle;
    private String answer;
    private int riddleStart;
    private int answerStart;
    private String answerDir;

    public Term(String riddle, String answer, int riddleStart, int answerStart, String answerDir) {
        this.riddle = riddle;
        this.answer = answer;
        this.riddleStart=riddleStart;
        this.answerStart = answerStart;
        this.answerDir = answerDir;
    }

    public String getRiddle() {
        return riddle;
    }

    public String getAnswer() {
        return answer;
    }

    public int getRiddleStart() {
        return riddleStart;
    }

    public int getAnswerStart() {
        return answerStart;
    }

    public String getAnswerDir() {
        return answerDir;
    }
}
