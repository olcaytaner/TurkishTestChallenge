package Similarity.SentenceSim;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gokhan on 22.03.2018.
 * Yapı sentence yerine String bazlı segmented unit yapısına göre düzenlendi.
 */
public class JaccardSimilarity implements SentenceSimilarity {

    @Override
    public float similarityScore(ArrayList<String> s1Units, ArrayList<String> s2units) {
        float similarityScore = 0.0f;
        List<String> intersectionWords = intersectionWords(s1Units, s2units);
        similarityScore = (float) intersectionWords.size() / (float) (s1Units.size() + s2units.size() - intersectionWords.size());
        return similarityScore;
    }

    private List<String> intersectionWords(List<String> questionDistictWords, List<String> choiceDistictWords) {
        List<String> intersect = questionDistictWords.stream()
                .filter(choiceDistictWords::contains)
                .collect(Collectors.toList());
        return intersect;
    }
}
