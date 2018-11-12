package com.michaelssss;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Michaelssss
 * @date 2018/11/12
 */
public class BeanFactory {

  public static Object initial(BeanDefinition beanDefinition) {
    try {
      Object instance = beanDefinition.getTargetClass().getConstructor(new Class[]{}).newInstance();
      Map<Method, Object> methodObjectMap = beanDefinition.getInjectFieldSetter();
      methodObjectMap.forEach((key, value) -> {
        try {
          key.invoke(instance, value);
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
      return instance;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
