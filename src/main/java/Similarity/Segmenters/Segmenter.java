package Similarity.Segmenters;

import java.util.ArrayList;

import Corpus.Sentence;
import Dictionary.Word;

public abstract class Segmenter {

    /*Algorithms for breaking words into multiple linguistic units(words, morphemes, syllables, letters) */
    public abstract ArrayList<String> segment(Word word);

    public ArrayList<String> segment(Sentence sentence){
        ArrayList<String> segments = new ArrayList<>();
        for (int i = 0; i < sentence.wordCount(); i++){
            segments.addAll(segment(sentence.getWord(i)));
        }
        return segments;
    }
}
