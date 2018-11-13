package com.michaelssss;

import java.util.Map;

/**
 * @author Michaelssss
 * @date 2018/11/13
 */
public interface BeanPropertyAccessor {

  Object getProperty(Object bean, String propertyName);

  Map<String, Object> getProperties(Object bean);

  void setProperty(Object bean, String propertyName, Object value);
}
