package Similarity;

import Similarity.Model.SentenceSimilarityModel;
import Similarity.Segmenters.Segmenter;
import Similarity.Segmenters.SyllableSegmenter;
import Similarity.Segmenters.WordSegmenter;
import Similarity.SentenceSim.JaccardSimilarity;
import Corpus.Sentence;

import Similarity.SentenceSim.SentenceSimilarity;
import Similarity.Service.ChallengeService;
import Similarity.Service.SimilarityChallengeResult;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.System.exit;

/**
 * Created by gokhan on 22.03.2018.
 */
public class ModelMain {

    //Notes: Bu className'ler combobox'tan seçilerek controller'a gelecek parametreler.
    //TODO:Spring'e taşıdıktan sonra silebilirsiniz.
    public static Object FakeController(int questionID, String similarityClassName, String segmenterClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ChallengeService similarityService = new ChallengeService();
        SentenceSimilarity similarity = (SentenceSimilarity) Class.forName(similarityClassName).newInstance();
        Segmenter segmenter = (Segmenter) Class.forName(segmenterClassName).newInstance();
        SentenceSimilarityModel model = new SentenceSimilarityModel(similarity,segmenter);
        SimilarityChallengeResult result = similarityService.ChallengeSimilarity(model,questionID);
        return result;      //TODO: Return that obj as a model for the view. (mvc framework specific.)
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        //TODO: Burası js ile rest request ile spring tarafından yapılacak.
        FakeController(1,"Similarity.SentenceSim.JaccardSimilarity","Similarity.Segmenters.SyllableSegmenter");
        exit(0);

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
                results.append(" | " + model.GetModelName());
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
