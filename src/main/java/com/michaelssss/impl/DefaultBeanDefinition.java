package com.michaelssss.impl;

import com.michaelssss.BeanDefinition;
import com.michaelssss.BeanPropertyAccessor;
import java.util.Collections;
import java.util.Map;

public class DefaultBeanDefinition implements BeanDefinition {

  private String id;

  private String classFullName;

  private Map<String, RefObject> injectFieldsReference;

  private BeanPropertyAccessor beanPropertyAccessor;


  private DefaultBeanDefinition() {
  }

  public Map<String, RefObject> getInjectFieldsReference() {
    return Collections.unmodifiableMap(this.injectFieldsReference);
  }

  private void setInjectFieldsReference(
      Map<String, RefObject> injectFieldsReference) {
    this.injectFieldsReference = injectFieldsReference;
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

  @Override
  public BeanPropertyAccessor getBeanPropertyAccessor() {
    if (null == this.beanPropertyAccessor) {
      this.beanPropertyAccessor = SimpleBeanPropertyAccessor.newInstance(getTargetClass());
    }
    return this.beanPropertyAccessor;
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