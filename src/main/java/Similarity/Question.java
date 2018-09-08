package Similarity;

import java.util.ArrayList;

public class Question {

    private String answer;
    private String questionString;
    private String P1Segment;
    private ArrayList<Sentences> paragraph;

    public Question() {

        this.answer = "";
        this.questionString = "";
        this.P1Segment="";
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public String getP1Segment() {
        return P1Segment;
    }

    public void setP1Segment(String p1Segment) {
        P1Segment = p1Segment;
    }

    public ArrayList<Sentences> getParagraph() {
        return paragraph;
    }

    public void setParagraph(ArrayList<Sentences>paragraf) {
        this.paragraph = paragraf;
    }
}
