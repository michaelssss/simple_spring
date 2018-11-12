package com.michaelssss;

import java.util.List;
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
      instances.put(beanDefinition, BeanUtils.initial(beanDefinition));
    }
  }

  @Override
  public Object getBean(String beanId) {
    List<BeanDefinition> beanDefinitionList = instances.keySet().parallelStream()
        .filter(beanDefinition -> beanDefinition.getId().equals(beanId))
        .collect(Collectors.toList());
    return beanDefinitionList.isEmpty() ? null : instances.get(beanDefinitionList.get(0));
  }
}
