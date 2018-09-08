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

    public SentenceSimilarity SimilarityAlgorithm;
    public Segmenter Segmenter;
    public String GetModelName(){
        return SimilarityAlgorithm.getClass().getSimpleName() + "-" + Segmenter.getClass().getSimpleName();
    }

    public SentenceSimilarityModel(SentenceSimilarity similarityAlgorithm, Segmenter segmenter) {
        if(similarityAlgorithm== null) throw new NullPointerException("similarityAlgorithm should not be null.");
        if(segmenter == null) throw new NullPointerException("segmenter should not be null.");
        SimilarityAlgorithm = similarityAlgorithm;
        Segmenter = segmenter;
    }

    public float CalculateSimilarity(Sentence s1, Sentence s2){
        ArrayList<String> s1Units = Segmenter.segment(s1);
        ArrayList<String> s2Units = Segmenter.segment(s2);
        return SimilarityAlgorithm.similarityScore(s1Units,s2Units);
    }
}