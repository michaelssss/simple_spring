package com.michaelssss;

public class TestBean {

  private String word;

  private TestBean testBean;

  public TestBean getTestBean() {
    return testBean;
  }

  public void setTestBean(TestBean testBean) {
    this.testBean = testBean;
  }

  @Override
  public String toString() {
    return "TestBean{" +
        "word='" + word + '\'' +
        '}';
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public void print() {
    System.out.println("Hello World :" + this.word);
    System.out.println("inner testbean: " + this.testBean);
  }
}
