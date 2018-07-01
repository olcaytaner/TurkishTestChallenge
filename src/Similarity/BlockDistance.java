package Similarity;

import Similarity.SentenceSim.OldSentenceSimilarity;
import Corpus.Sentence;
import Dictionary.Word;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BlockDistance implements OldSentenceSimilarity {
    public float similarityScore(Sentence s1, Sentence s2) {//her iki cümle uzunluğunu alıp daha sonra az olan cümlenin kelimeri bitince scorunu 0 olarak aldım.
        int similarityScore = 0;
        int s1WordCnt = s1.wordCount();
        int s2WordCnt = s2.wordCount();
        int maxWordCount = Math.max(s1WordCnt, s2WordCnt);
        for (int wordIndex = 0; wordIndex < maxWordCount; wordIndex++) {
            int questionScore = 0, choiceScore = 0;
            if (s1WordCnt > wordIndex) {
                questionScore = WordCountSentence(s1, s1.getWord(wordIndex));
            }
            if (s1WordCnt > wordIndex) {
                choiceScore = WordCountSentence(s2, s1.getWord(wordIndex));
            }
            similarityScore += Math.abs(questionScore - choiceScore);
        }
        return (float) similarityScore / maxWordCount;
    }

    private int WordCountSentence(Sentence sentence, Word sentenceWord) {
        ArrayList<Word> sentenceWords = sentence.getWords();
        int totalSameWords = sentence.getWords().stream().
                filter(word -> word.getName().equals(sentenceWord.getName())).
                collect(Collectors.toList()).size();
        return totalSameWords;
    }
}
