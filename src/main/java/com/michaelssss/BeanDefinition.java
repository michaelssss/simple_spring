package com.michaelssss;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
public interface BeanDefinition {

    String getId();

    Class<?> getTargetClass();

    Map<Method, Object> getInjectFieldSetter();


}
