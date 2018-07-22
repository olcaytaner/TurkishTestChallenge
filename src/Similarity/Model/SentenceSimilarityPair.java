package Similarity.Model;


import java.io.Serializable;

/**
 * Created by gokhan on 22.07.2018.
 */
public class SentenceSimilarityPair implements Serializable {

    public SentenceSimilarityPair() {
    }

    public SentenceSimilarityPair(String sentence1, String sentence2, float similarity) {
        Sentence1 = sentence1;
        Sentence2 = sentence2;
        Similarity = similarity;
    }

    public SentenceSimilarityPair(String sentence2, String sentence1) {
        Sentence2 = sentence2;
        Sentence1 = sentence1;
    }

    public String Sentence1;
    public String Sentence2;
    public float Similarity;

}
