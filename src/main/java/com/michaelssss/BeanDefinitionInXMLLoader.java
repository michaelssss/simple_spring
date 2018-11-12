package com.michaelssss;

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

public class BeanDefinitionInXMLLoader {

  private String relatePath;

  private Document document;

  private BeanDefinitionInXMLLoader() {
  }

  private BeanDefinitionInXMLLoader(String relatePath) {
    this.relatePath = relatePath;
    this.document = null;
    loadFile();
  }

  public static BeanDefinitionInXMLLoader newInstance(String relatePath) {
    return new BeanDefinitionInXMLLoader(relatePath);
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
      Map<String, Object> map = new HashMap<>();
      for (Element element1 : subElements) {
        String fieldName = element1.attribute("name").getValue();
        String fieldValue = element1.getStringValue();
        map.put(fieldName, fieldValue);
      }
      ((DefaultBeanDefinition) beanDefinition).setInjectFieldsReference(map);
      result.add(beanDefinition);
    }
    return result;
  }
}
