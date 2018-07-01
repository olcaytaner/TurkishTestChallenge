package Similarity.WordNet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

import Math.*;
import WordNet.Similarity.Similarity;
import WordNet.Similarity.WuPalmer;
import WordNet.*;

public class SentenceLevelSimilarity {

	public static void main(String[] args) {
		// Construct WordNet, sentences and dictionary
		WordNet english = new WordNet("Data/Wordnet/english_wordnet_version_31.xml", new Locale("en"));
		ArrayList<SynSet> sentence1 = constructSentence1(english);
		ArrayList<SynSet> sentence2 = constructSentence2(english);

		ArrayList<ArrayList<SynSet>> sentences = new ArrayList<ArrayList<SynSet>>();
		sentences.add(sentence1);
		sentences.add(sentence2);
		ArrayList<SynSet> dictionary = constructDictionary(sentences);
		// Dictionary can be displayed for debugging purposes
		// printDictionary(dictionary);
		// Choose the similarity metric and pass it as a parameter
		WuPalmer wp = new WuPalmer(english);
		Vector vector1 = constructSentenceVector(sentence1, dictionary, wp);
		Vector vector2 = constructSentenceVector(sentence2, dictionary, wp);

		double cosineSimilarity = -1;
		// Code will raise an exception if dimension mismatch occurs.
		try {
			cosineSimilarity = vector1.cosineSimilarity(vector2);
		} catch (VectorSizeMismatch vectorSizeMismatch) {
			vectorSizeMismatch.printStackTrace();
		}
		// Print the cos similarity and vectors.
		double sum1 = 0, sum2 = 0;
		System.out.println("SynSet\t V1\t V2");
		for (int i = 0; i < dictionary.size(); i++) {
			System.out.println(dictionary.get(i).representative() + " "
					+ String.format("%.2f", vector2.getValue(i)) + " " + String.format("%.2f", vector2.getValue(i)));
			sum1 += vector1.getValue(i) * vector1.getValue(i);
			sum2 += vector2.getValue(i) * vector2.getValue(i);
		}
		System.out.println("Vector lengths => V1:" + sum1 + " V2:" + sum2);
		System.out.println("Cos Sim => " + String.format("%.2f", cosineSimilarity));
	}


	// This function implements the sentence vector construction algorithm in
	// the paper. It computes similarity of each token of dictionary and
	// sentence. Sets the maximum of the similarities as the resulting
	// similarity
	private static Vector constructSentenceVector(ArrayList<SynSet> sentence, ArrayList<SynSet> dictionary, Similarity similarity) {
		int dictionarySize = dictionary.size();
		double[] vector = new double[dictionarySize];
		for (int i = 0; i < dictionarySize; i++) {
			SynSet dictionaryToken = dictionary.get(i);
			double maxSimilarityScore = 0;
			for (SynSet sentenceToken : sentence) {
				double similarityScore = similarity.computeSimilarity(dictionaryToken, sentenceToken);
				maxSimilarityScore = Math.max(maxSimilarityScore, similarityScore);
			}
			vector[i] = maxSimilarityScore;
		}
		return new Vector(vector);
	}

	// Below two functions just create sample sentences.
	private static ArrayList<SynSet> constructSentence1(WordNet english) {
		// Sentence1: The supervised system exploiting the results of the
		// unsupervised similarity computation methods is illustrated in figure
		// 6.
		ArrayList<SynSet> sentence1 = new ArrayList<SynSet>();
		sentence1.add(english.getSynSetWithId("ENG31-02358744-a")); // Supervised
		sentence1.add(english.getSynSetWithId("ENG31-05911466-n")); // System
		sentence1.add(english.getSynSetWithId("ENG31-01165070-v")); // Exploit
		sentence1.add(english.getSynSetWithId("ENG31-11430739-n")); // Result
		sentence1.add(english.getSynSetWithId("ENG31-02358892-a")); // Unsupervised
		sentence1.add(english.getSynSetWithId("ENG31-04750845-n")); // Similarity
		sentence1.add(english.getSynSetWithId("ENG31-00870588-n")); // Computation
		sentence1.add(english.getSynSetWithId("ENG31-05668113-n")); // Method
		sentence1.add(english.getSynSetWithId("ENG31-01672316-v")); // Illustrate
		sentence1.add(english.getSynSetWithId("ENG31-07012307-n")); // Figure
		sentence1.add(english.getSynSetWithId("ENG31-13766862-n")); // 6
		return sentence1;
	}

	private static ArrayList<SynSet> constructSentence2(WordNet english) {
		// Sentence2: We combined our unsupervised semantic similarity measures
		// within a supervised method.
		ArrayList<SynSet> sentence2 = new ArrayList<SynSet>();
		sentence2.add(english.getSynSetWithId("ENG31-00395719-v")); // Combine
		sentence2.add(english.getSynSetWithId("ENG31-02358892-a")); // Unsupervised
		sentence2.add(english.getSynSetWithId("ENG31-02852927-a")); // Semantic
		sentence2.add(english.getSynSetWithId("ENG31-04750845-n")); // Similarity
		sentence2.add(english.getSynSetWithId("ENG31-13598374-n")); // Measure
		sentence2.add(english.getSynSetWithId("ENG31-00111558-r")); // Within
		sentence2.add(english.getSynSetWithId("ENG31-02358744-a")); // Supervised
		sentence2.add(english.getSynSetWithId("ENG31-05668113-n")); // Method

		return sentence2;

	}

	// This function takes all sentences as input and unions them.
	private static ArrayList<SynSet> constructDictionary(ArrayList<ArrayList<SynSet>> sentences) {
		HashSet<SynSet> dictionary = new HashSet<SynSet>();
		for (ArrayList<SynSet> sentence : sentences) {
			dictionary.addAll(sentence);
		}
		return new ArrayList<>(dictionary);
	}

	// A function just for debugging purposes.
	private static void printDictionary(ArrayList<SynSet> dictionary) {
		for (SynSet synset : dictionary) {
			System.out.println(synset.getId() + " " + synset.representative());
		}
		System.out.println("There are " + dictionary.size() + " words in the dictionary");

	}
}
