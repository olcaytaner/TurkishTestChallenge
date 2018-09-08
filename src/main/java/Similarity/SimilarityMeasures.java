package Similarity;

import Corpus.Sentence;
import Dictionary.Word;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimilarityMeasures {
    private Sentence questionSentence = new Sentence();
    private Sentence choiceSentence = new Sentence();

    SimilarityMeasures(Sentence questionSentence, Sentence choiceSentence) {
        this.questionSentence = questionSentence;
        this.choiceSentence = choiceSentence;
    }

    public int QgramSimilarity() {//sorulucak
        return 0;
    }

    public int LevenshteinDistance() {//Sadece kelimeler arasındaki farklılıklar var boşlukları ihmal ederek yaptım.
        int similartyScore = 0;
        int questionWordCount = this.questionSentence.wordCount();
        int chocieWordCount = this.choiceSentence.wordCount();
        int totalLoopCount = Math.max(questionWordCount, chocieWordCount);
        for (int wordIndex = 0; wordIndex < totalLoopCount; wordIndex++) {
            if (questionWordCount > wordIndex && chocieWordCount > wordIndex) {
                similartyScore += blockDistanceWords(this.choiceSentence.getWord(wordIndex).getName(), this.questionSentence.getWord(wordIndex).getName());
            } else {
                if (questionWordCount <= wordIndex) {
                    similartyScore += this.choiceSentence.getWord(wordIndex).getName().length();
                } else {
                    similartyScore += this.questionSentence.getWord(wordIndex).getName().length();
                }
            }
        }
        return similartyScore;
    }

    public int BlockDistance() {//her iki cümle uzunluğunu alıp daha sonra az olan cümlenin kelimeri bitince scorunu 0 olarak aldım.
        int similarityScore = 0;
        int questionWordCount = this.questionSentence.wordCount();
        int chocieWordCount = this.choiceSentence.wordCount();
        int totalLoopCount = Math.max(questionWordCount, chocieWordCount);
        for (int wordIndex = 0; wordIndex < totalLoopCount; wordIndex++) {
            int questionScore = 0, choiceScore = 0;
            if (questionWordCount > wordIndex) {
                questionScore = WordCountSentence(this.questionSentence, this.questionSentence.getWord(wordIndex));
            }
            if (chocieWordCount > wordIndex) {
                choiceScore = WordCountSentence(this.choiceSentence, this.questionSentence.getWord(wordIndex));
            }
            similarityScore += Math.abs(questionScore - choiceScore);
        }
        return similarityScore;
    }

    public double JaccardSimilarity() {//kökleri değil kelimenin tamamınıkullandım.
        double similarityScore = 0.0;
        List<Word> questionDistictWords = DistinctWordList(this.questionSentence);
        List<Word> choiceDistictWords = DistinctWordList(this.choiceSentence);
        List<Word> intersectionWords = intersectionWords(questionDistictWords, choiceDistictWords);
        similarityScore = (double) intersectionWords.size() / (double) (questionDistictWords.size() + choiceDistictWords.size()-intersectionWords.size());
        return similarityScore;
    }

    public double OverlapCoefficient() {//kökleri değil kelimenin tamamını kullandım
        double similarityScore = 0.0;
        List<Word> questionDistictWords = DistinctWordList(this.questionSentence);
        List<Word> choiceDistictWords = DistinctWordList(this.choiceSentence);
        List<Word> intersectionWords = intersectionWords(questionDistictWords, choiceDistictWords);
        similarityScore = (double) intersectionWords.size() / (double) (Math.min(this.questionSentence.getWords().size(), this.choiceSentence.getWords().size()));
        return similarityScore;
    }

    private int WordCountSentence(Sentence sentence, Word sentenceWord) {
        ArrayList<Word> sentenceWords = sentence.getWords();
        int totalSameWords = sentence.getWords().stream().
                filter(word -> word.getName().equals(sentenceWord.getName())).
                collect(Collectors.toList()).size();
        return totalSameWords;
    }

    private List<Word> DistinctWordList(Sentence sentence) {
        List<Word> distictWord = sentence.getWords().stream().
                distinct().collect(Collectors.toList());
        return distictWord;
    }

    private List<Word> intersectionWords(List<Word> questionDistictWords, List<Word> choiceDistictWords) {
        List<Word> intersect = questionDistictWords.stream()
                .filter(choiceDistictWords::contains)
                .collect(Collectors.toList());
        return intersect;
    }

    private int blockDistanceWords(String firstWord, String secondWord) {
        int result = 0, smallLength = 0, totalDiffChr = 0;
        int bigLength = 0;
        if (firstWord.length() > secondWord.length()) {
            smallLength = secondWord.length();
            bigLength = firstWord.length();
        } else {
            smallLength = firstWord.length();
            bigLength = secondWord.length();
        }
        for (int index = 0; index < smallLength; index++) {
            if (firstWord.charAt(index) != secondWord.charAt(index)) {
                totalDiffChr++;
            }
        }
        result = totalDiffChr + (bigLength - smallLength);
        return result;
    }
}
