package com.michaelssss;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class XMLApplicationContext implements ApplicationContext {

  private final Map<BeanDefinition, Object> instances = new ConcurrentHashMap<>();

  public XMLApplicationContext(String relatePath) {
    BeanDefinitionInXMLLoader beanDefinitionInXMLLoader = BeanDefinitionInXMLLoader
        .newInstance(relatePath);
    Set<BeanDefinition> beanDefinitions = beanDefinitionInXMLLoader.parse();
    for (BeanDefinition beanDefinition : beanDefinitions) {
      instances.put(beanDefinition, BeanFactory.initial(beanDefinition));
    }
  }

  @Override
  public Object getBean(String beanId) {
    return instances.getOrDefault(instances.keySet().parallelStream()
        .filter(beanDefinition -> beanDefinition.getId().equals(beanId))
        .collect(Collectors.toList()).get(0), null);
  }
}
