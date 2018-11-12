package com.michaelssss.impl;

import com.michaelssss.ApplicationContext;
import com.michaelssss.BeanDefinition;
import com.michaelssss.BeanDefinitionLoader;
import com.michaelssss.BeanFactory;
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
    BeanDefinitionLoader beanDefinitionLoader = BeanDefinitionLoader
        .newInstance(relatePath);
    Set<BeanDefinition> beanDefinitions = beanDefinitionLoader.parse();
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
