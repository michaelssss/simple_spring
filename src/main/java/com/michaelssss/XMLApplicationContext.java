package com.michaelssss;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class XMLApplicationContext implements ApplicationContext {

    private final Map<BeanDefinition, Object> instances = new ConcurrentHashMap<>();

    public XMLApplicationContext(String relatePath) {
        BeanDefinitionInXMLLoadder beanDefinitionInXMLLoadder = BeanDefinitionInXMLLoadder.newInstance(relatePath);
        Set<BeanDefinition> beanDefinitions = beanDefinitionInXMLLoadder.parse();
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                Object instance = beanDefinition.getTargetClass().getConstructor(new Class[]{}).newInstance();
                Map<Method, Object> methodObjectMap = beanDefinition.getInjectFieldSetter();
                methodObjectMap.forEach((key, value) -> {
                    try {
                        key.invoke(instance, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                instances.put(beanDefinition, instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object getBean(String beanId) {
        return instances.getOrDefault(instances.keySet().parallelStream().filter(beanDefinition -> beanDefinition.getId().equals(beanId)).collect(Collectors.toList()).get(0), null);
    }
}
