package com.michaelssss.impl;

import com.michaelssss.BeanProperty;

/**
 * @author Michaelssss
 * @date 2018/11/13
 */
public class SimpleBeanProperty implements BeanProperty {

  private String name;

  public SimpleBeanProperty(String name) {
    this.name = name;
  }

  @Override
  public String getSetMethod() {
    StringBuffer buffer = new StringBuffer(name);
    buffer = buffer.replace(0, 1, buffer.substring(0, 1).toUpperCase());
    buffer.insert(0, "set".toCharArray(), 0, 3);
    return buffer.toString();
  }

  @Override
  public String getGetMethod() {
    StringBuffer buffer = new StringBuffer(name);
    buffer = buffer.replace(0, 1, buffer.substring(0, 1).toUpperCase());
    buffer.insert(0, "get".toCharArray(), 0, 3);
    return buffer.toString();
  }
}
