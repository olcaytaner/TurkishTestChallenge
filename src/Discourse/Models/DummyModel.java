package TestChallenge.Discourse.Models;

import TestChallenge.Discourse.Evaluation.Metrics;
import TestChallenge.Discourse.ParseXML.QT8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DummyModel extends BaseModel implements Metrics {

	@Override
	public ArrayList<ArrayList<QT8>> predict(ArrayList<ArrayList<QT8>> answers, ArrayList<ArrayList<QT8>> words) {
		ArrayList<ArrayList<QT8>> predictions = new ArrayList<ArrayList<QT8>>();
		for (int i = 0; i < answers.size(); i++) {
			Random rand = new Random();
			ArrayList<QT8> answer = answers.get(i);
			ArrayList<QT8> prediction = (ArrayList<QT8>) answer.clone();
			Collections.shuffle(prediction, rand);
			predictions.add(prediction);
		}
		return predictions;
	}
}