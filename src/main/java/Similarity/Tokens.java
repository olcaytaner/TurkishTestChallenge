package Similarity;

import java.util.ArrayList;

public class Tokens {

    private ArrayList<Token> tokens = new ArrayList<Token>();

    public Tokens() {
        this.tokens = new ArrayList<Token>();
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> token) {
        this.tokens = token;
    }
}
