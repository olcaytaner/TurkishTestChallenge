package TestChallenge.Discourse.ParseXML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

	public static Document getXMLDocument(File inputFile)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(inputFile);
		return document;
	}

	public static ArrayList<ArrayList<ArrayList<QT8>>> extractSentenceQuestion(NodeList nodelist,
			ArrayList<ArrayList<QT8>> sentences, ArrayList<ArrayList<QT8>> answers, ArrayList<ArrayList<QT8>> words,
			boolean returnWords) {

		ArrayList<ArrayList<ArrayList<QT8>>> sentenceQuestion = new ArrayList<ArrayList<ArrayList<QT8>>>();
		ArrayList<QT8> sentence = new ArrayList<QT8>();
		ArrayList<QT8> answer = new ArrayList<QT8>();
		ArrayList<QT8> word = new ArrayList<QT8>();

		// Loop for traversing all nodes of sentence and answer.
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
			if (node.getNodeName().equals("sentence")) {
				QT8 q = new QT8();
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					q.setSentence(node.getFirstChild().getNodeValue());
				}
				sentence.add(q);
			} else if (node.getNodeName().equals("token") && returnWords) {
				QT8 q = new QT8();
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					q.setText(node.getFirstChild().getNodeValue());
				}
				word.add(q);
			} else if (node.getNodeName().equals("ans")) {
				QT8 q = new QT8();
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					q.setAnswer(node.getFirstChild().getNodeValue());
				}
				answer.add(q);
			} else if (node.hasChildNodes()) {
				extractSentenceQuestion(node.getChildNodes(), sentences, answers, words, returnWords);
			}
		}
		if (sentence.size() > 0) {
			sentences.add(sentence);

		}
		if (answer.size() > 0) {
			answers.add(answer);

		}
		if (returnWords && word.size() > 0) {
			words.add(word);

		}
		sentenceQuestion.add(sentences);
		sentenceQuestion.add(answers);
		sentenceQuestion.add(words);
		return sentenceQuestion;
	}

}
