package Similarity;

import java.util.ArrayList;

public class Instances {

    public ArrayList<QType> getQuestionProperty() {
        return questionProperty;
    }

    public Instances() {
        questionProperty = new ArrayList<QType>();
    }

    public void setQuestionProperty(ArrayList<QType> questionProperty) {
        this.questionProperty = questionProperty;
    }

    private ArrayList<QType> questionProperty = new ArrayList<QType>();


}
