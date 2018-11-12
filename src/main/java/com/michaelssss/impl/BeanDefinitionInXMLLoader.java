package com.michaelssss.impl;

import com.michaelssss.BeanDefinition;
import com.michaelssss.BeanDefinitionInLoader;
import com.michaelssss.InitialBeanFailedException;
import com.michaelssss.utils.StringUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BeanDefinitionInXMLLoader implements BeanDefinitionInLoader {

  private String relatePath;

  private Document document;

  private BeanDefinitionInXMLLoader() {
  }

  public BeanDefinitionInXMLLoader(String relatePath) {
    this.relatePath = relatePath;
    this.document = null;
    loadFile();
  }

  private void loadFile() {
    try {
      SAXReader reader = new SAXReader();
      this.document = reader
          .read(Thread.currentThread().getContextClassLoader().getResourceAsStream(relatePath));
    } catch (DocumentException e) {
      throw new InitialBeanFailedException("parse XML failed");
    }
  }

  @Override
  public Set<BeanDefinition> parse() {
    Element rootElement = this.document.getRootElement();
    Iterator<Element> elementIterator = rootElement.elementIterator("bean");
    Set<BeanDefinition> result = new HashSet<>();
    while (elementIterator.hasNext()) {
      Element element = elementIterator.next();
      Attribute id = element.attribute("id");
      Attribute clazz = element.attribute("class");
      BeanDefinition beanDefinition = new DefaultBeanDefinition();
      ((DefaultBeanDefinition) beanDefinition).setId(id.getValue());
      ((DefaultBeanDefinition) beanDefinition).setClassFullName(clazz.getValue());
      List<Element> subElements = element.elements("field");
      Map<String, RefObject> map = new HashMap<>();
      for (Element element1 : subElements) {
        String fieldName = element1.attribute("name").getValue();
        RefObject refObject = new RefObject();
        String fieldDefinition = element1.attributeValue("value");
        if (StringUtils.isNotEmpty(fieldDefinition)) {
          refObject.setDirectValue(fieldDefinition);
        } else {
          refObject.setRefBeanId(element1.attributeValue("ref"));
        }
        map.put(fieldName, refObject);
      }
      ((DefaultBeanDefinition) beanDefinition).setInjectFieldsReference(map);
      result.add(beanDefinition);
    }
    return result;
  }
}