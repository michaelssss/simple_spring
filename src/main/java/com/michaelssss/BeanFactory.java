package com.michaelssss;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michaelssss
 * @date 2018/11/12
 */
public class BeanFactory {

  private final Map<String, Object> instances = new ConcurrentHashMap<>();

  private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

  public static BeanFactory newInstance(Map<String, BeanDefinition> beanDefinitionMap) {
    BeanFactory beanFactory = new BeanFactory();
    beanFactory.beanDefinitionMap.putAll(beanDefinitionMap);
    return beanFactory;
  }

  public Object doGetBean(String beanId) {
    if (beanDefinitionMap.get(beanId) == null) {
      throw new InitialBeanFailedException("no such bean");
    }
    Object result = instances.get(beanId);
    if (result == null) {
      result = doCreateBean(beanId);
      instances.put(beanId, result);
    }
    return result;
  }

  private Object doCreateBean(String beanId) {
    return doInject(BeanUtils.initial(this.beanDefinitionMap.get(beanId)),
        this.beanDefinitionMap.get(beanId));
  }

  private Object doInject(Object instance, BeanDefinition beanDefinition) {
    Map<Method, RefObject> methodObjectMap = beanDefinition.getInjectFieldsReference();
    methodObjectMap.forEach((key, value) -> {
      try {
        if (value.isDirectObject()) {
          key.invoke(instance, value.getDirectValue());
        } else {
          key.invoke(instance, this.doGetBean(value.getRefBeanId()));
        }
      } catch (Exception e) {
        throw new InitialBeanFailedException("inject field value failed");
      }
    });
    return instance;
  }
}
