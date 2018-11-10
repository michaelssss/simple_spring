package com.michaelssss;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DefaultApplicationContext implements ApplicationContext {
    private final Map<BeanDefinition, Object> instances = new ConcurrentHashMap<>();

    public DefaultApplicationContext(){
        BeanDefinition beanDefinition = new DefaultBeanDefinition("testBean","com.michaelssss.TestBean",new HashSet<String>());
        try {
            Object instance = beanDefinition.getTargetClass().getConstructor(new Class[]{}).newInstance();
            instances.put(beanDefinition,instance);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public Object getBean(String beanId) {
        return instances.getOrDefault(instances.keySet().parallelStream().filter(beanDefinition -> beanDefinition.getId().equals(beanId)).collect(Collectors.toList()).get(0), null);
    }
}
