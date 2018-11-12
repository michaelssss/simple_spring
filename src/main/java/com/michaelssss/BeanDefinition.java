package com.michaelssss;

import java.lang.reflect.Method;
import java.util.Map;

public interface BeanDefinition {

  String getId();

  Class<?> getTargetClass();

  Map<Method, RefObject> getInjectFieldsReference();
}
