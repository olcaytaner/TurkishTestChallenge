package Similarity.Service;

import Corpus.Sentence;
import Similarity.Model.SentenceSimilarityPair;
import Similarity.QType;
import Similarity.Sentences;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gokhan on 22.07.2018.
 */
public class SimilarityChallengeResult implements Serializable {

    public ArrayList<SentenceSimilarityPair> Pairs = new ArrayList<>();
    public QType Question;

    //public ArrayList<Sentences> Segments = new ArrayList<>();
    //TODO: Segments eklenecek.

}
