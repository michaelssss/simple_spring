package com.michaelssss;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Michaelssss
 * @date 2018/11/12
 */
public class BeanUtils {

  public static Object initial(BeanDefinition beanDefinition) {
    try {
      return inject(beanDefinition,
          beanDefinition.getTargetClass().getConstructor(new Class[]{}).newInstance());
    } catch (Exception e) {
      throw new InitialBeanFailedException("unknow exception" + e.getMessage());
    }
  }

  public static Object inject(BeanDefinition beanDefinition, Object instance) {
    Map<Method, Object> methodObjectMap = beanDefinition.getInjectFieldSetter();
    methodObjectMap.forEach((key, value) -> {
      try {
        key.invoke(instance, value);
      } catch (Exception e) {
        throw new InitialBeanFailedException("inject field value failed");
      }
    });
    return instance;
  }
}
