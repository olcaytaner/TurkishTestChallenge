package Similarity;

public class QType {
    private String id;
    private String etCode;
    private String p;
    private String q;
    private String ref;
    private Question question;
    private Choices choices;

    public QType() {

        this.id = "";
        this.etCode = "";
        this.p = "";
        this.q = "";
        this.ref = "";
        this.question = new Question();
        this.choices = new Choices();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtCode() {
        return etCode;
    }

    public void setEtCode(String etCode) {
        this.etCode = etCode;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Choices getChoices() {
        return choices;
    }

    public void setChoices(Choices choices) {
        this.choices = choices;
    }

}
