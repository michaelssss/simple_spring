package com.michaelssss;

import com.michaelssss.impl.RefObject;
import java.lang.reflect.Method;
import java.util.Map;

public interface BeanDefinition {

  String getId();

  Class<?> getTargetClass();

  Map<Method, RefObject> getInjectFieldsReference();
}
