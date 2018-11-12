package com.michaelssss;

import com.michaelssss.utils.StringUtils;

/**
 * @author Michaelssss
 * @date 2018/11/12
 */
public class RefObject {

  private Object directValue;

  private String refBeanId;

  public Object getDirectValue() {
    return directValue;
  }

  public void setDirectValue(Object directValue) {
    this.directValue = directValue;
  }

  public String getRefBeanId() {
    return refBeanId;
  }

  public void setRefBeanId(String refBeanId) {
    this.refBeanId = refBeanId;
  }

  public boolean isDirectObject() {
    return StringUtils.isEmpty(this.refBeanId);
  }
}
