package Similarity;

import Similarity.Model.SentenceSimilarityModel;
import Similarity.Segmenters.SyllableSegmenter;
import Similarity.Segmenters.WordSegmenter;
import Similarity.SentenceSim.JaccardSimilarity;
import Corpus.Sentence;

import javafx.util.Pair;
import java.util.ArrayList;

/**
 * Created by gokhan on 22.03.2018.
 */
public class ModelMain {

    public static void main(String[] args) {
        //populate model configurations here. Potentially Alg# X segmenter# model configuration candidates.
        Log("Populating Models...");
        ArrayList<SentenceSimilarityModel> models = new ArrayList<>();
        models.add(new SentenceSimilarityModel(new JaccardSimilarity(),new SyllableSegmenter()));
        models.add(new SentenceSimilarityModel(new JaccardSimilarity(), new WordSegmenter()));

        //Init. SentenceSim. experiment here.
        Log("Initializing experiment samples...");
        ArrayList<Pair<Sentence,Sentence>> pairs = new ArrayList<>();
        pairs.add(new Pair<>(new Sentence("Bugün yemek yedim"), new Sentence("Bugün yemek yiyebilirdim")));
        pairs.add(new Pair<>(new Sentence("Hava sıcakken dışarıda top oynuyabilirsin."), new Sentence("Sıcak havada top oynayayım")));

        //Run Models with your input data (questions.)
        StringBuilder results = new StringBuilder();
        int i = 1;
        for (Pair<Sentence,Sentence> pair : pairs) {
            for (SentenceSimilarityModel model : models) {
                float sim = model.CalculateSimilarity(pair.getKey(),pair.getValue());
                results.append(i + ": ");
                results.append(pair.getKey().toString() + " ==? " + pair.getValue().toString());
                results.append(" | " + model.getModelName());
                results.append(" | " + sim);
                results.append("\n");
            }
            i++;
        }
        Log("Displaying experiment results: ");     //TODO: Bu tabloyu sabit boyutlu column'lar (padleft/padright'lar) ile birlikte daha okunabilir yaparsın.
        Log(results.toString());
    }

    public static void Log(String message) {
        System.out.println(message);
    }
}
