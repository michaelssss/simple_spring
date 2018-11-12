package com.michaelssss;

import com.michaelssss.impl.RefObject;
import com.michaelssss.utils.BeanUtils;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michaelssss
 * @date 2018/11/12
 */
public class BeanFactory {

  private static volatile BeanFactory beanFactory = null;

  private final Map<String, Object> instances = new ConcurrentHashMap<>();

  private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

  /**
   * @param beanDefinitionMap {beanId:BeanDefinition}
   * @return BeanFactory实例
   */
  public static BeanFactory newInstance(Map<String, BeanDefinition> beanDefinitionMap) {
    if (BeanFactory.beanFactory == null) {
      synchronized (BeanFactory.class) {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.beanDefinitionMap.putAll(beanDefinitionMap);
        BeanFactory.beanFactory = beanFactory;
      }
    }
    return beanFactory;
  }

  /**
   * @param beanId BeanId
   * @return Bean
   */
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
