package Similarity.Service;

import Corpus.Sentence;
import Similarity.*;
import Similarity.Model.SentenceSimilarityModel;
import Similarity.Model.SentenceSimilarityPair;
import Similarity.Segmenters.Segmenter;

import java.util.ArrayList;

/**
 * Created by gokhan on 22.07.2018.
 */
public class ChallengeService {

    public SimilarityChallengeResult ChallengeSimilarity(SentenceSimilarityModel model, int qid){

        SimilarityChallengeResult result = new SimilarityChallengeResult();

        //Load question
        //TODO: Load question fromxml by qid or something else.

        //Manually loading question
        Question q = new Question();
        q.setQuestionString("Aşağıdaki soruyu cevaplayınız.");
        QType qType = new QType();
        qType.setQuestion(q);
        Choices choices = new Choices();
        qType.setChoices(choices);
        ArrayList<Sentences> sentences = new ArrayList<Sentences>();
        AddSentence(sentences,"ali topa bak", model.Segmenter);
        AddSentence(sentences,"topa bak ali", model.Segmenter);
        AddSentence(sentences,"bak topa ali", model.Segmenter);
        AddSentence(sentences,"ali bakma bir yere", model.Segmenter);
        AddSentence(sentences,"ali ne yaptın", model.Segmenter);
        choices.setSentences(sentences);
        result.Question = qType;

        //create pairs
        Sentence s1 = new Sentence(sentences.get(0).getText());
        for (int i = 1; i <sentences.size() ; i++) {
            Sentences sentence = sentences.get(i);
            Sentence s = new Sentence(sentence.getText());
            float sim = model.CalculateSimilarity(s1,s);
            SentenceSimilarityPair pair = new SentenceSimilarityPair(s1.toString(),s.toString(),sim);
            result.Pairs.add(pair);
        }
        return result;
    }
    private void AddSentence(ArrayList<Sentences> sentences, String sentence, Segmenter segmenter){
        Sentences s = new Sentences(sentence);
        SetTokens(s, segmenter.segment(new Sentence(sentence)));
        sentences.add(s);
    }
    private void SetTokens(Sentences s, ArrayList<String> segments){
        ArrayList<Token> tokenArr = new ArrayList<>();
        for (String segment : segments) {
            Token token = new Token();
            token.setText(segment);
            tokenArr.add(token);
        }
        Tokens tokens = new Tokens();
        tokens.setTokens(tokenArr);
        s.setToken(tokens);
    }
}
