package com.michaelssss;

import java.lang.reflect.Method;
import java.util.Set;
public interface BeanDefinition {

    String getId();

    Class<?> getTargetClass();

    Set<Method> getInjectFieldSetter();
}
