package com.adwaith;


import java.util.ArrayList;
import java.util.List;

class TokenTypes {
    public static final String TT_INT = "INT";
    public static final String TT_FLOAT = "FLOAT";
    public static final String TT_PLUS = "PLUS";
    public static final String TT_MINUS = "MINUS";
    public static final String TT_MUL = "MUL";
    public static final String TT_DIV = "DIV";
    public static final String TT_LPAREN = "LPAREN";
    public static final String TT_RPAREN = "RPAREN";
}


class Tokens{
    private String type;
    private String value;

    Tokens(String type, String value){
        this.type = type;
        this.value = value;
    }
    Tokens(String type){
        this.type = type;
        this.value = null;
    }
    @Override
    public String toString(){
        if(value == null){
            return type;
        }
        else{
            return type + ":" + value;
        }
    }

    public String getType(){
        return type;
    }
    public String getValue(){
        return value;
    }
}


class Lexer{
    TokenTypes tokenTypes = new TokenTypes();
    List<Tokens> tokens = new ArrayList<>();
    String text;
    int pos = -1;
    Character current_char;
    Lexer(String text){
        this.text = text;
        advance();
    }

    private void advance(){
        this.pos += 1;
        if(pos >= text.length()){
            current_char = '\0';
        }
        else{
            this.current_char = text.charAt(pos);
        }
    }

    private void makeNumber(){
        StringBuilder numStr = new StringBuilder();
        int dotCount = 0; boolean isFloat = false;
        while(current_char!= null && (Character.isDigit(current_char) || current_char == '.')){
            if (current_char == '.'){
                if (dotCount == 0){
                    dotCount += 1;
                }
                else{
                    //Custom Error for invalid decimal points
                }
            isFloat = true;
            }
            numStr.append(current_char);
            advance();
        }
        if (isFloat){
            tokens.add(new Tokens(TokenTypes.TT_FLOAT, numStr.toString()));
        } else {
            tokens.add(new Tokens(TokenTypes.TT_INT, numStr.toString()));
        }
    }



    void makeTokens(){
        tokens.clear();
        while(current_char != '\0'){
            if(Character.isWhitespace(current_char)){
                advance();
            } else if(Character.isDigit(current_char) || current_char == '.'){
                makeNumber();
            } else if (current_char == '+') {
                tokens.add(new Tokens(TokenTypes.TT_PLUS));
                advance();
            } else if (current_char == '-'){
                tokens.add(new Tokens(TokenTypes.TT_MINUS));
                advance();
            } else if (current_char == '*'){
                tokens.add(new Tokens(TokenTypes.TT_MUL));
                advance();
            } else if (current_char == '/') {
                tokens.add(new Tokens(TokenTypes.TT_DIV));
                advance();
            } else if (current_char == '('){
                tokens.add(new Tokens(TokenTypes.TT_LPAREN));
                advance();
            } else if (current_char == ')'){
                tokens.add(new Tokens(TokenTypes.TT_RPAREN));
                advance();
            }
        }
    }

    public List<Tokens> getTokens(){
        return tokens;
    }


}


class Run{
    String text;
    Lexer lexer;
    Run(String text){
        this.text = text;
        this.lexer = new Lexer(text);
    }

    public List<Tokens> run() {
        lexer.makeTokens();
        return lexer.getTokens();
    }

}


public class wLang {
}
