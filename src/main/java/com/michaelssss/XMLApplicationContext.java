package com.michaelssss;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class XMLApplicationContext implements ApplicationContext {

  private BeanFactory beanFactory;

  private String relatePath;

  public XMLApplicationContext(String relatePath) {
    this.relatePath = relatePath;
    beanFactory = BeanFactory.newInstance(load());
  }

  private Map<String, BeanDefinition> load() {
    BeanDefinitionInXMLLoader beanDefinitionInXMLLoader = BeanDefinitionInXMLLoader
        .newInstance(relatePath);
    Set<BeanDefinition> beanDefinitions = beanDefinitionInXMLLoader.parse();
    Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    beanDefinitions.parallelStream().forEach(beanDefinition ->
        beanDefinitionMap.put(beanDefinition.getId(), beanDefinition)
    );
    return beanDefinitionMap;
  }

  @Override
  public Object getBean(String beanId) {
    return beanFactory.doGetBean(beanId);
  }
}
