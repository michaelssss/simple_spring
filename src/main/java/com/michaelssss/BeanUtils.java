package com.michaelssss;

/**
 * @author Michaelssss
 * @date 2018/11/12
 */
public class BeanUtils {

  public static Object initial(BeanDefinition beanDefinition) {
    try {
      return beanDefinition.getTargetClass().getConstructor(new Class[]{}).newInstance();
    } catch (Exception e) {
      throw new InitialBeanFailedException("unknow exception" + e.getMessage());
    }
  }
}
