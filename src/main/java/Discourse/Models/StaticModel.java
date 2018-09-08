package Discourse.Models;

import Discourse.Evaluation.Metrics;
import Discourse.ParseXML.QT8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class StaticModel extends BaseModel implements Metrics {

	private String[] baslangic, devam, sonuc;
	
	public StaticModel(String[] baslangic, String[] devam, String[] sonuc) {
		this.baslangic = baslangic;
		this.devam = devam;
		this.sonuc = sonuc;
	}
	@Override
	public ArrayList<ArrayList<QT8>> predict(ArrayList<ArrayList<QT8>> answers, ArrayList<ArrayList<QT8>> words) {
		ArrayList<ArrayList<QT8>> predictions = new ArrayList<ArrayList<QT8>>();
		ArrayList<ArrayList<QT8>> predictionsWtihMaxScore = new ArrayList<ArrayList<QT8>>();
		ArrayList<QT8> predictionWtihMaxScore = new ArrayList<QT8>();
		int wB = 1, wD = 1, wS = 1;

		for (int i = 0; i < answers.size(); i++) {
			ArrayList<QT8> answer = answers.get(i);
			ArrayList<QT8> answerClone = (ArrayList<QT8>) answer.clone();
			predictions = listPermutations(answerClone);

			ArrayList<Double> scoreBas = new ArrayList<Double>();
			ArrayList<Double> scoreDev = new ArrayList<Double>();
			ArrayList<Double> scoreSon = new ArrayList<Double>();
			ArrayList<Double> scoreFinal = new ArrayList<Double>();

			// Get scores of each sentence in one permutation case.
			scoreBas = scoreBas(words, predictions, baslangic);
			scoreDev = scoreDev(words, predictions, devam);
			scoreSon = scoreSon(words, predictions, sonuc);

			// Calculate final score.
			for (int k = 0; k < predictions.size(); k++) {
				scoreFinal.add(wB * scoreBas.get(k) + wD * scoreDev.get(k) + wS * scoreSon.get(k));
			}
			// Find the index of max final score.
			int indexMax = scoreFinal.indexOf(Collections.max(scoreFinal));
			// Get the permutation which gives maximum score.
			predictionWtihMaxScore = predictions.get(indexMax);
			predictionsWtihMaxScore.add(predictionWtihMaxScore);
		}
		return predictionsWtihMaxScore;
	}

	// Create permutation list from answers.
	private static ArrayList<ArrayList<QT8>> listPermutations(ArrayList<QT8> list) {
		if (list.size() == 0) {
			ArrayList<ArrayList<QT8>> result = new ArrayList<ArrayList<QT8>>();
			result.add(new ArrayList<QT8>());
			return result;
		}
		ArrayList<ArrayList<QT8>> returnMe = new ArrayList<ArrayList<QT8>>();
		QT8 firstElement = list.remove(0);
		ArrayList<ArrayList<QT8>> recursiveReturn = listPermutations(list);
		for (ArrayList<QT8> li : recursiveReturn) {
			for (int index = 0; index <= li.size(); index++) {
				ArrayList<QT8> temp = new ArrayList<QT8>(li);
				temp.add(index, firstElement);
				returnMe.add(temp);
			}
		}
		return returnMe;
	}

	// Find score of a starting sentence.
	private static ArrayList<Double> scoreBas(ArrayList<ArrayList<QT8>> word, ArrayList<ArrayList<QT8>> permutations,
			String[] baslangic) {
		ArrayList<Double> scores = new ArrayList<Double>();
		double N;
		int index;
		for (int j = 0; j < permutations.size(); j++) {
			N = permutations.get(j).size();
			double scoreB = 0;
			for (int i = 0; i < N; i++) {
				index = Integer.parseInt(permutations.get(j).get(i).toString(1));
				scoreB += ((N - i) / N) * (pBas(word.get(index - 1), baslangic));
			}
			scores.add(scoreB);
		}
		return scores;
	}

	// Find score of a middle sentence.
	private static ArrayList<Double> scoreDev(ArrayList<ArrayList<QT8>> word, ArrayList<ArrayList<QT8>> permutations, String[] devam) {
		ArrayList<Double> scores = new ArrayList<Double>();
		double N;
		int index;
		for (int j = 0; j < permutations.size(); j++) {
			N = permutations.get(j).size();
			double scoreD = 0;
			for (int i = 0; i < N; i++) {
				if (i + 1 < Math.ceil(N / 2)) {
					index = Integer.parseInt(permutations.get(j).get(i).toString(1));
					scoreD += ((N - (N - i - 2)) / (N - 1)) * (pDev(word.get(index - 1),devam));
				} else if (i + 1 == Math.ceil(N / 2) && N % 2 != 0) {
					index = Integer.parseInt(permutations.get(j).get(i).toString(1));
					scoreD += 1 * (pDev(word.get(index - 1),devam));
				} else if (i + 1 == Math.ceil(N / 2) || i + 1 == (Math.ceil(N / 2) + 1)) {
					index = Integer.parseInt(permutations.get(j).get(i).toString(1));
					scoreD += 1 * (pDev(word.get(index - 1),devam));
				} else {
					index = Integer.parseInt(permutations.get(j).get(i).toString(1));
					scoreD += ((N - i + 1) / (N - 1)) * (pDev(word.get(index - 1),devam));
				}
			}
			scores.add(scoreD);
		}
		return scores;
	}

	// Find score of a ending sentence.
	private static ArrayList<Double> scoreSon(ArrayList<ArrayList<QT8>> word, ArrayList<ArrayList<QT8>> permutations,
			String[] sonuc) {
		ArrayList<Double> scores = new ArrayList<Double>();
		double N;
		int index;
		for (int j = 0; j < permutations.size(); j++) {
			N = permutations.get(j).size();
			double scoreS = 0;
			for (int i = 0; i < N; i++) {
				index = Integer.parseInt(permutations.get(j).get(i).toString(1));
				scoreS += ((N - (N - i - 1)) / N) * (pSon(word.get(index - 1), sonuc));
			}
			scores.add(scoreS);
		}
		return scores;
	}

	// Probability within given starting discourse words.
	private static double pBas(ArrayList<QT8> word, String[] baslangic) {
		ArrayList<String> Bas = new ArrayList<String>();
		Bas.addAll(Arrays.asList(baslangic));
		double pB = 0;
		double defaultpB = 0.5;
		int numOfMatches = 0;
		for (QT8 w : word) {
			for (String b : Bas) {
				if (w.toString(2).toLowerCase().contains(b)) {
					numOfMatches++;
				}
			}
		}
		if (numOfMatches == 0) {
			pB = defaultpB * word.size();
		} else {
			pB = numOfMatches + (defaultpB * (word.size() - numOfMatches));
		}
		return (pB / word.size());
	}

	// Probability within given continuous discourse words.
	private static double pDev(ArrayList<QT8> word, String[] devam) {
		ArrayList<String> Dev = new ArrayList<String>();
		Dev.addAll(Arrays.asList(devam));
		double pD = 0;
		double defaultpD = 0.5;
		int numOfMatches = 0;
		for (QT8 w : word) {
			for (String d : Dev) {
				if (w.toString(2).toLowerCase().contains(d)) {
					numOfMatches++;
				}
			}
		}
		if (numOfMatches == 0) {
			pD = defaultpD * word.size();
		} else {
			pD = numOfMatches + (defaultpD * (word.size() - numOfMatches));
		}
		return pD / word.size();
	}

	// Probability within given ending discourse words.
	private static double pSon(ArrayList<QT8> word, String[] sonuc) {
		ArrayList<String> Son = new ArrayList<String>();
		Son.addAll(Arrays.asList(sonuc));
		double pS = 0;
		double defaultpS = 0.5;
		int numOfMatches = 0;
		for (QT8 w : word) {
			for (String s : Son) {
				if (w.toString(2).toLowerCase().contains(s)) {
					numOfMatches++;
				}
			}
		}
		if (numOfMatches == 0) {
			pS = defaultpS * word.size();
		} else {
			pS = numOfMatches + (defaultpS * (word.size() - numOfMatches));
		}
		return pS / word.size();
	}
}