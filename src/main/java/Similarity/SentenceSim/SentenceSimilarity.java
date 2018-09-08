package Similarity.SentenceSim;

import java.util.ArrayList;

public interface SentenceSimilarity {

    float similarityScore(ArrayList<String> s1Units, ArrayList<String> s2units);    //Artık her sentence temel yapısı String olan Unit'ler listesinden oluşuyor.

}
