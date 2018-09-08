package Similarity.SentenceSim;

import Corpus.Sentence;

/*@Deprecated TODO: Mevcut kodları/düzenin bozulması diye silmedim. Bu eski versiyon. Yeni yapıya geçersen SentenceSimilarity'yi kullanırsın.*/
public interface OldSentenceSimilarity {

    float similarityScore(Sentence s1, Sentence s2);

}
