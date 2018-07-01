package Similarity;

import Similarity.SentenceSim.OldSentenceSimilarity;
import Corpus.Sentence;
import Dictionary.Word;

import java.util.List;
import java.util.stream.Collectors;

public class JaccardSimilarityIOld implements OldSentenceSimilarity {

    public float similarityScore(Sentence s1, Sentence s2) { //kökleri değil kelimenin tamamınıkullandım.
        float similarityScore = 0.0f;
        List<Word> questionDistictWords = DistinctWordList(s1);
        List<Word> choiceDistictWords = DistinctWordList(s2);
        List<Word> intersectionWords = intersectionWords(questionDistictWords, choiceDistictWords);
        similarityScore = (float) intersectionWords.size() / (float) (questionDistictWords.size() + choiceDistictWords.size() - intersectionWords.size());
        return similarityScore;
    }

    private List<Word> intersectionWords(List<Word> questionDistictWords, List<Word> choiceDistictWords) {
        List<Word> intersect = questionDistictWords.stream()
                .filter(choiceDistictWords::contains)
                .collect(Collectors.toList());
        return intersect;

    }

    private List<Word> DistinctWordList(Sentence sentence) {
        List<Word> distictWord = sentence.getWords().stream().
                distinct().collect(Collectors.toList());
        return distictWord;
    }
}
