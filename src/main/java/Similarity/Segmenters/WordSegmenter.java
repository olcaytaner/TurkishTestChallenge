package Similarity.Segmenters;

import Dictionary.Word;

import java.util.ArrayList;

/**
 * Created by gokhan on 22.03.2018.
 */
public class WordSegmenter extends Segmenter {

    public ArrayList<String> segment(Word word) {
        ArrayList<String> segments = new ArrayList<>();
        segments.add(word.getName());
        return segments;
    }
}
