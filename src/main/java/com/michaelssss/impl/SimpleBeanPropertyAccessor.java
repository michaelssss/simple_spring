package com.michaelssss.impl;

import com.michaelssss.BeanPropertyAccessor;
import com.michaelssss.BeanPropertyAccessorException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Michaelssss
 * @date 2018/11/13
 */
public class SimpleBeanPropertyAccessor implements BeanPropertyAccessor {

  private Class<?> clazz;

  private SimpleBeanPropertyAccessor() {
  }

  public static BeanPropertyAccessor newInstance(Class<?> clazz) {
    SimpleBeanPropertyAccessor simpleBeanPropertyAccessor = new SimpleBeanPropertyAccessor();
    simpleBeanPropertyAccessor.clazz = clazz;
    return simpleBeanPropertyAccessor;
  }

  @Override
  public Object getProperty(Object bean, String propertyName) {
    SimpleBeanProperty simpleBeanProperty = new SimpleBeanProperty(propertyName);
    try {
      Method method = clazz
          .getMethod(simpleBeanProperty.getGetMethod(), new Class[]{});
      return method.invoke(bean);
    } catch (Exception e) {
      throw new BeanPropertyAccessorException(e.getMessage());
    }
  }

  @Override
  public Map<String, Object> getProperties(Object bean) {
    Set<String> fields = new HashSet<>();
    for (Field field : clazz.getDeclaredFields()) {
      fields.add(field.getName());
    }
    Map<String, Object> result = new HashMap<>();
    fields.parallelStream()
        .forEach(fieldName -> result.put(fieldName, getProperty(bean, fieldName)));
    return result;
  }

  @Override
  public void setProperty(Object bean, String propertyName, Object value) {
    SimpleBeanProperty simpleBeanProperty = new SimpleBeanProperty(propertyName);
    try {
      Method method = clazz
          .getMethod(simpleBeanProperty.getSetMethod(), value.getClass());
      method.invoke(bean, value);
    } catch (Exception e) {
      throw new BeanPropertyAccessorException(e.getMessage());
    }
  }
}
