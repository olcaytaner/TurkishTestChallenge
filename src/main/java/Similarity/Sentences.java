package Similarity;

public class Sentences {

    private String text;
    private Tokens tokens;

    public Sentences(){
        this.text="";
        this.tokens=new Tokens();
    }

    public Sentences(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Tokens getToken() {
        return tokens;
    }

    public void setToken(Tokens tokens) {
        this.tokens = tokens;
    }
}
