package com.michaelssss;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DefaultBeanDefinition implements BeanDefinition {
    private String id;

    private String classFullName;

    private Map<String, Object> injectFieldsReference;

    public DefaultBeanDefinition(String id, String classFullName, Map<String, Object> injectFieldsName) {
        this.id = id;
        this.classFullName = classFullName;
        this.injectFieldsReference = injectFieldsName;
    }

    public DefaultBeanDefinition() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public Map<String, Object> getInjectFieldsReference() {
        return injectFieldsReference;
    }

    public void setInjectFieldsReference(Map<String, Object> injectFieldsReference) {
        this.injectFieldsReference = injectFieldsReference;
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

    public Map<Method, Object> getInjectFieldSetter() {
        Class<?> clazz = getTargetClass();
        Map<Method, Object> map = new HashMap<>();
        Map<String, Object> methodNames = DefaultBeanDefinition.getSetterString(this.injectFieldsReference);
        for (Map.Entry<String, Object> entry : methodNames.entrySet()) {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(entry.getKey()))
                    map.put(method, entry.getValue());
            }
        }
        return map;
    }

    public static Map<String, Object> getSetterString(Map<String, Object> fields) {
        Map<String, Object> result = new HashMap<>();
        fields.forEach((key, value) -> {
            result.put("set" + key.replace(key.charAt(0), (char) Character.toUpperCase(key.codePointAt(0))), value);
        });
        return result;
    }
}
