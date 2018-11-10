package com.michaelssss;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultBeanDefinition implements BeanDefinition {
    private String id;

    private String classFullName;

    private Set<String> injectFieldsName;

    public DefaultBeanDefinition(String id, String classFullName, Set<String> injectFieldsName) {
        this.id = id;
        this.classFullName = classFullName;
        this.injectFieldsName = injectFieldsName;
    }

    public String getId() {
        return id;
    }

    public Class<?> getTargetClass() {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(classFullName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public Set<Method> getInjectFieldSetter() {
        Class<?> clazz = getTargetClass();
        Set<Method> methods = new HashSet<>();
        Set<String> methodNames = DefaultBeanDefinition.getSetterString(injectFieldsName);
        for(String methodName:methodNames){
            try {
                methods.add(clazz.getMethod(methodName));
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            }
        }
        return methods;
    }
    public static Set<String> getSetterString(Set<String> fields){
        return fields.parallelStream().map(string->{
            return "set"+string.replace(string.charAt(0),(char) Character.toUpperCase(string.codePointAt(0)));
        }).collect(Collectors.toSet());
    }
}
