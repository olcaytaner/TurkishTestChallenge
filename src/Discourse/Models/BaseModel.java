package Discourse.Models;

import Discourse.ParseXML.QT8;
import java.util.ArrayList;

public abstract class BaseModel {

	// Generate a prediction
	public abstract ArrayList<ArrayList<QT8>> predict(ArrayList<ArrayList<QT8>> answers, ArrayList<ArrayList<QT8>> words);

}
