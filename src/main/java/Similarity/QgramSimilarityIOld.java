package Similarity;

import Similarity.SentenceSim.OldSentenceSimilarity;
import Corpus.Sentence;
import Dictionary.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QgramSimilarityIOld implements OldSentenceSimilarity {

    int k=0;
    QgramSimilarityIOld(int kthCharacter){
        this.k=kthCharacter;
    }

    public float similarityScore(Sentence s1, Sentence s2) {
        ArrayList<Word> s1Words=s1.getWords();
        ArrayList<Word> s2Words=s2.getWords();
        List<Word> joinWords=new ArrayList<Word>();
        joinWords= Stream.concat(s1Words.stream(),s2Words.stream()).collect(Collectors.toList());
        int totalWordLength=joinWords.stream().mapToInt(i -> i.getName().length()).sum();
        int distance=calculateDistance(s1,s2);
        return (float)1.0-(float)distance/totalWordLength;
    }

    private int calculateDistance(Sentence s1, Sentence s2){
        int totalDistance=0;
        HashMap<String,Integer> s1Hash=hashQgramSentence(s1);
        HashMap<String,Integer> s2Hash=hashQgramSentence(s2);
        totalDistance=SentenceDistance(s1Hash,s2Hash);
        return totalDistance;
    }

    private HashMap<String,Integer> hashQgramSentence(Sentence sentence){
        HashMap<String,Integer> result=new HashMap<>();
        String strSntc=sentence.toString().replace(" ","");
        int totalTry=strSntc.length()-this.k+1;
        for(int i=0;i<totalTry;i++){
            String pos = strSntc.substring(i, i + k);
            if(result.containsKey(pos)){
                result.put(pos, result.get(pos) + 1);
            }else{
                result.put(pos,1);
            }
        }
        return result;
    }

    private int SentenceDistance(HashMap<String,Integer> s1,HashMap<String, Integer> s2) {
        int distance = 0;
        List<String> s1List = new ArrayList<>(s1.keySet());
        List<String> s2List = new ArrayList<>(s2.keySet());
        for (int k = 0; k < s1List.size(); k++) {
            if (s2.containsKey(s1List.get(k))) {
                distance += Math.abs(s2.get(s1List.get(k)) - s1.get(s1List.get(k)));
            } else {
                distance += s1.get(s1List.get(k));
            }
        }
        for (int k = 0; k < s2List.size(); k++) {
            if (!s1.containsKey(s2List.get(k))) {
                distance += s2.get(s2List.get(k));
            }
        }
        return distance;
    }
}
