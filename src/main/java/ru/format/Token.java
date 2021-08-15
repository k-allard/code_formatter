package ru.format;

import java.util.ArrayList;
import java.util.List;

public class Token {
    public List<String> codeStringList = new ArrayList<String>();
    private int level = 0;

    public Token(String token) {
        this.codeStringList.add(token);
    }

    public void increaseLevel() {
        level++;
    }

    @Override
    public String toString() {
        return codeStringList.toString();
    }


}
