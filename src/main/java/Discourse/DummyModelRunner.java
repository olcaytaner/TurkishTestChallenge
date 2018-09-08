package Discourse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import Discourse.Evaluation.Metrics;
import Discourse.Models.DummyModel;
import Discourse.ParseXML.QT8;
import Discourse.ParseXML.XMLParser;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DummyModelRunner extends DummyModel {		//TODO: refactor to single Runner instance, single Main, multiple Models.

	public static void main(String[] args) {
		ArrayList<ArrayList<ArrayList<QT8>>> sentenceQuestion = new ArrayList<ArrayList<ArrayList<QT8>>>();		//TODO: We can define our own structures(Sentences,Answer,DataSet) instead of ArrayList of ArrayList definitions.
		ArrayList<ArrayList<QT8>> sentences = new ArrayList<ArrayList<QT8>>();
		ArrayList<ArrayList<QT8>> answers = new ArrayList<ArrayList<QT8>>();
		ArrayList<ArrayList<QT8>> words = new ArrayList<ArrayList<QT8>>();
		ArrayList<ArrayList<QT8>> predictions = new ArrayList<ArrayList<QT8>>();
		
		double accuracy = 0.0;
		File inputFile, outputFile;
		String inputFileName = "Data\\TestChallenge\\qt8.xml";
		String outputFileName = "qt8_dummy_experiment.txt";

		inputFile = new File(inputFileName);
		outputFile = new File(outputFileName);

		try {
			Document document = XMLParser.getXMLDocument(inputFile);
			if (document.hasChildNodes()) {
				System.out.println("Parsing file...");
				sentenceQuestion = XMLParser.extractSentenceQuestion(document.getChildNodes(), sentences, answers,
						words, false);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println(e.getMessage());
		}

		answers = sentenceQuestion.get(1);
		DummyModel dm = new DummyModel();
		predictions = dm.predict(answers, words);
		// Optional argument for logging report, if not need please remove true
		accuracy = Metrics.accuracy(predictions, answers, sentences, outputFile, true);
		System.out.println("\nAccuracy: " + accuracy);
	}
}