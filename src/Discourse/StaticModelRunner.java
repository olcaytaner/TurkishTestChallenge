package Discourse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import Discourse.Evaluation.Metrics;
import Discourse.Models.StaticModel;
import Discourse.ParseXML.QT8;
import Discourse.ParseXML.XMLParser;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class StaticModelRunner extends StaticModel {

	static String[] baslangic = new String[] { "gün", "tanım", "başlangıç", "ilk", "mutluluk", "ver", "bir", "üzere" };
	static String[] devam = new String[] { "ayrıca", "yine", "yanısıra", "ama", "hep", "orta", "çok" };
	static String[] sonuc = new String[] { "sonuç", "son", "olarak", "yazık", "ken", "keyif" };

	public StaticModelRunner(String[] baslangic, String[] devam, String[] sonuc) {
		super(baslangic, devam, sonuc);
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<ArrayList<QT8>>> sentenceQuestion = new ArrayList<ArrayList<ArrayList<QT8>>>();
		ArrayList<ArrayList<QT8>> sentences = new ArrayList<ArrayList<QT8>>();
		ArrayList<ArrayList<QT8>> answers = new ArrayList<ArrayList<QT8>>();
		ArrayList<ArrayList<QT8>> words = new ArrayList<ArrayList<QT8>>();
		ArrayList<ArrayList<QT8>> predictions = new ArrayList<ArrayList<QT8>>();
		double accuracy = 0.0;
		File inputFile, outputFile;
		String inputFileName = "Data\\TestChallenge\\qt8.xml";
		String outputFileName = "qt8_static_experiment.txt";

		inputFile = new File(inputFileName);
		outputFile = new File(outputFileName);

		try {
			Document document = XMLParser.getXMLDocument(inputFile);
			if (document.hasChildNodes()) {
				System.out.println("Parsing file...");
				sentenceQuestion = XMLParser.extractSentenceQuestion(document.getChildNodes(), sentences, answers,
						words, true);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println(e.getMessage());
		}
		sentences = sentenceQuestion.get(0);
		answers = sentenceQuestion.get(1);
		words = sentenceQuestion.get(2);

		StaticModel sm = new StaticModel(baslangic, devam, sonuc);
		predictions = sm.predict(answers, words);
		// Optional argument for logging report, if not need please remove true.
		accuracy = Metrics.accuracy(predictions, answers, sentences, outputFile, true);
		System.out.println("\nAccuracy: " + accuracy);
	}
}
