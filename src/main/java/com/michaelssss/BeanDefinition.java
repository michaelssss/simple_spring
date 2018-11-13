package com.michaelssss;

import com.michaelssss.impl.RefObject;
import java.util.Map;

/**
 * 该接口用于描述一个Bean的信息
 */
public interface BeanDefinition {

  /**
   * @return Bean的Id
   */
  String getId();

  /**
   * @return Bean所对应的Class
   */
  Class<?> getTargetClass();

  /**
   * @return Bean需要注入的字段及注入引用对象
   */
  Map<String, RefObject> getInjectFieldsReference();

  BeanPropertyAccessor getBeanPropertyAccessor();
}
