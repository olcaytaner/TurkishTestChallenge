package Discourse.ParseXML;

// Class for QT8 excel.
public class QT8 {
	private int id;
	private String exam;
	private String etCode;
	private int p;
	private int q;
	private String question;
	private String sentence;
	private String answer;
	private String text;

	public QT8() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public String getETCode() {
		return etCode;
	}

	public void setETCode(String etCode) {
		this.etCode = etCode;
	}

	public int getp() {
		return p;
	}

	public void setp(int p) {
		this.p = p;
	}

	public int getq() {
		return q;
	}

	public void setq(int q) {
		this.q = q;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String toString(int i) {
		if (i == 0)
			return this.sentence;
		else if (i == 1)
			return this.answer;
		else
			return this.text;
	}
}