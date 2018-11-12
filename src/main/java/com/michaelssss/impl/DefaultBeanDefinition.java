package com.michaelssss.impl;

import com.michaelssss.BeanDefinition;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DefaultBeanDefinition implements BeanDefinition {

  private String id;

  private String classFullName;

  private Map<String, RefObject> injectFieldsReference;


  private DefaultBeanDefinition() {
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

  private void setInjectFieldsReference(
      Map<String, RefObject> injectFieldsReference) {
    this.injectFieldsReference = getSetterString(injectFieldsReference);
  }

  public String getClassFullName() {
    return classFullName;
  }

  private void setClassFullName(String classFullName) {
    this.classFullName = classFullName;
  }

  public String getId() {
    return id;
  }

  private void setId(String id) {
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

  public static final class DefaultBeanDefinitionBuilder {

    private DefaultBeanDefinition defaultBeanDefinition;

    private DefaultBeanDefinitionBuilder() {
      defaultBeanDefinition = new DefaultBeanDefinition();
    }

    public static DefaultBeanDefinitionBuilder aDefaultBeanDefinition() {
      return new DefaultBeanDefinitionBuilder();
    }

    public DefaultBeanDefinitionBuilder withId(String id) {
      defaultBeanDefinition.setId(id);
      return this;
    }

    public DefaultBeanDefinitionBuilder withClassFullName(String classFullName) {
      defaultBeanDefinition.setClassFullName(classFullName);
      return this;
    }

    public DefaultBeanDefinitionBuilder withInjectFieldsReference(
        Map<String, RefObject> injectFieldsReference) {
      defaultBeanDefinition.setInjectFieldsReference(injectFieldsReference);
      return this;
    }

    public DefaultBeanDefinition build() {
      return defaultBeanDefinition;
    }
  }
}