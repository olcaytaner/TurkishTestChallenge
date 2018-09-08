package Similarity.Segmenters;

import Dictionary.Word;
import Syllibification.IrregularWordException;
import Syllibification.SyllableList;

import java.util.ArrayList;

/**
 * Created by gokhan on 22.03.2018.
 */
public class SyllableSegmenter extends Segmenter {

    public ArrayList<String> segment(Word word) {
        try {
            SyllableList syllableList = new SyllableList(word.getName());
            return syllableList.getSyllables();
        } catch (IrregularWordException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
