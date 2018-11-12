package com.michaelssss.impl;

import com.michaelssss.BeanDefinition;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DefaultBeanDefinition implements BeanDefinition {

  private String id;

  private String classFullName;

  private Map<String, RefObject> injectFieldsReference;


  public DefaultBeanDefinition() {
  }

  private static Map<String, RefObject> getSetterString(Map<String, RefObject> fields) {
    Map<String, RefObject> refObjectMap = new HashMap<>();
    fields.forEach((key, value) -> {
      refObjectMap
          .put("set" + key.replaceFirst(String.valueOf(key.charAt(0)),
              String.valueOf(Character.toUpperCase(key.charAt(0)))),
              value);
    });
    return refObjectMap;
  }

  public Map<Method, RefObject> getInjectFieldsReference() {
    Map<Method, RefObject> methodRefObjectMap = new HashMap<>();
    Method[] methods = getTargetClass().getMethods();
    this.injectFieldsReference.forEach((key, value) -> {
      for (Method method : methods) {
        if (method.getName().equals(key)) {
          methodRefObjectMap.put(method, value);

        }
      }
    });
    return methodRefObjectMap;
  }

  public void setInjectFieldsReference(
      Map<String, RefObject> injectFieldsReference) {
    this.injectFieldsReference = getSetterString(injectFieldsReference);
  }

  public String getClassFullName() {
    return classFullName;
  }

  public void setClassFullName(String classFullName) {
    this.classFullName = classFullName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Class<?> getTargetClass() {
    Class<?> clazz = null;
    try {
      clazz = Class.forName(classFullName);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return clazz;
  }
}
