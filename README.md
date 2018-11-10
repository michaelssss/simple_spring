# 从头开始写Spring

## 2018-11-10

首先我们从平时使用Spring的场景中提取最基本的操作

1. 我们通过Spring管理实例

2. 我们通过Spring来创建实例

3. 我们通过Spring来完成依赖注入

那我们的从以上三点来设计一个我们自己的Spring版本

### 分析

那我们设计一个最小场景，即在XML中定义Bean，然后通过ApplicationContext来获取Bean

既然我们习惯性的将Class的实例称之为Bean，那么我们就可以新建个接口称之为BeanDefinition

```java
public interface BeanDefinition{
    String getId();
    Class<?> getTargetClass();
    Set<Method> getInjectFieldSetter();
}
```

这个接口用于Bean的定义

新建一个ApplicationContext用于获取Bean实例