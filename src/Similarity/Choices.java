package Similarity;

import java.util.ArrayList;

public class Choices {

    private ArrayList<Sentences> sentences = new ArrayList<Sentences>();

    public Choices() {

        this.sentences = new ArrayList<Sentences>();
    }

    public ArrayList<Sentences> getSentences() {
        return sentences;
    }

    public void setSentences(ArrayList<Sentences> sentences) {
        this.sentences = sentences;
    }


}
