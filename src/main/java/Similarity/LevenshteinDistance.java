package Similarity;

import Similarity.SentenceSim.OldSentenceSimilarity;
import Corpus.Sentence;

public class LevenshteinDistance implements OldSentenceSimilarity {
    public float similarityScore(Sentence s1, Sentence s2) {//Normal algorithmaya göre yapıldı normalizasyon için hocaya danışılıcak.
        int similartyScore = 0;
        int questionWordCount = s1.wordCount();
        int chocieWordCount = s2.wordCount();
        int totalLoopCount = Math.max(questionWordCount, chocieWordCount);
        for (int wordIndex = 0; wordIndex < totalLoopCount; wordIndex++) {
            if (questionWordCount > wordIndex && chocieWordCount > wordIndex) {
                similartyScore += levenshteinDistance(s2.getWord(wordIndex).getName(), s1.getWord(wordIndex).getName());
            } else {
                if (questionWordCount <= wordIndex) {
                    similartyScore += s2.getWord(wordIndex).getName().length();
                } else {
                    similartyScore += s1.getWord(wordIndex).getName().length();
                }
            }
        }
        return 1.0f-(float) similartyScore / (float) (s1.toString().length() + s2.toString().length());
    }

    public static int levenshteinDistance(String s1, String s2){
        int edits[][]=new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++)
            edits[i][0]=i;
        for(int j=1;j<=s2.length();j++)
            edits[0][j]=j;
        for(int i=1;i<=s1.length();i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int u = (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1);
                edits[i][j] = Math.min(
                        edits[i - 1][j] + 1,
                        Math.min(
                                edits[i][j - 1] + 1,
                                edits[i - 1][j - 1] + u
                        )
                );
            }
        }
        return edits[s1.length()][s2.length()];
    }
}
