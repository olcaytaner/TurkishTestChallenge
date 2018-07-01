package Similarity.Model;

import Similarity.Segmenters.Segmenter;
import Similarity.SentenceSim.SentenceSimilarity;
import Corpus.Sentence;

import java.util.ArrayList;

/**
 * Created by gokhan on 22.03.2018.
 * Model includes both segmenter and Similarity algorithm implementations.
 */
public class SentenceSimilarityModel {

    public SentenceSimilarity similarityAlgorithm;
    public Segmenter segmenter;
    public String getModelName(){
        return similarityAlgorithm.getClass().getSimpleName() + "-" + segmenter.getClass().getSimpleName();
    }

    public SentenceSimilarityModel(SentenceSimilarity similarityAlgorithm, Segmenter segmenter) {
        if (similarityAlgorithm == null){
            throw new NullPointerException("SimilarityAlgorithm should not be null.");
        }
        if (segmenter == null){
            throw new NullPointerException("Segmenter should not be null.");
        }
        this.similarityAlgorithm = similarityAlgorithm;
        this.segmenter = segmenter;
    }

    public float CalculateSimilarity(Sentence s1, Sentence s2){
        ArrayList<String> s1Units = segmenter.segment(s1);
        ArrayList<String> s2Units = segmenter.segment(s2);
        return similarityAlgorithm.similarityScore(s1Units,s2Units);
    }

}
