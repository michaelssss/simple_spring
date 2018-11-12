package com.michaelssss;

/**
 * 该接口表示一个描述BeanDefinition的Resource内存对象
 */
public interface ApplicationContext {

  /**
   * 根据BeanId获取Bean实例，若已经实例化则返回实例化，若是未实例化则实例化后返回
   *
   * @param beanId BeanId
   * @return 实例化结果
   */
  Object getBean(String beanId);
}
