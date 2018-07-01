package TestChallenge.Discourse.Evaluation;

import TestChallenge.Discourse.ParseXML.QT8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


// Class for calculations of score, probability and accuracy.
//TODO:
public interface Metrics {

	public static double accuracy(ArrayList<ArrayList<QT8>> predictions, ArrayList<ArrayList<QT8>> answers,
								  ArrayList<ArrayList<QT8>> sentences, File outputFile, boolean... logToFile) {
		Integer falseCounts = 0, trueCounts = 0;
		double accuracy = 0.0;
		clearLogFile(outputFile, logToFile);

		int questionNum = 1;
		for (int i = 0; i < answers.size(); i++) {
			log("\nQuestion " + questionNum++ + ": ", true, outputFile, logToFile);
			log("\nqid " + answers + ": ", true, outputFile, logToFile);

			ArrayList<QT8> sentence = sentences.get(i);

			log("\nSentence: ", true, outputFile, logToFile);
			for (QT8 s : sentence) {
				log(s.toString(0) + " ", true, outputFile, logToFile);
			}

			ArrayList<QT8> answer = answers.get(i);

			log("\nAnswer: ", true, outputFile, logToFile);
			for (QT8 a : answer) {
				log(a.toString(1) + " ", false, outputFile, logToFile);
			}

			ArrayList<QT8> prediction = predictions.get(i);

			log("\n", true, outputFile, logToFile);
			log("Prediction: ", true, outputFile, logToFile);
			for (QT8 p : prediction) {
				log(p.toString(1) + " ", false, outputFile, logToFile);
			}

			if (prediction.equals(answer)) {
				trueCounts++;
				log("\n", true, outputFile, logToFile);
				log("=>Right", true, outputFile, logToFile);
			} else {
				falseCounts++;
				log("\n", true, outputFile, logToFile);
				log("=>Wrong", true, outputFile, logToFile);
			}
		}
		accuracy = (trueCounts / (double)(trueCounts + falseCounts));
		System.out.println("\nCorrect/All: " + trueCounts + "/" + (trueCounts+falseCounts));
		log("\n=>Accuracy: " + accuracy, true, outputFile, logToFile);
		if (logToFile.length >= 1)
			System.out.println("\nDetailed report is in " + outputFile);
		return accuracy;
	}

	// Clear file.
	static void clearLogFile(File outputFile, boolean... logToFile) {
		//
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(outputFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		pw.close();
	}

	static void log(String message, boolean ln, File outputFile, boolean... logToFile) {
		if (logToFile.length >= 1) {
			PrintWriter out;
			try {
				out = new PrintWriter(new FileWriter(outputFile, true), true);
				if (ln)
					out.println(message);
				else
					out.write(message);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
