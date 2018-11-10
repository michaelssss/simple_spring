package com.michaelssss;

public class TestBean {
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void print() {
        System.out.println("Hello World :" + this.word);
    }
}
