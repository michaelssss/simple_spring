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

```java
public interface ApplicationContext {
    Object getBean(String beanId);
}

```

建立测试类

```java
public class Test {
    @org.junit.Test
    public void test() {
        ApplicationContext applicationContext = new DefaultApplicationContext();
        TestBean testBean = (TestBean) applicationContext.getBean("testBean");
        Assert.assertNotNull(testBean);
        Assert.assertTrue(TestBean.class.isInstance(testBean));
    }
}
```

可以发现正常实例化了对象

但是当前的实例化还是写在了DemoApplicationContext并没有将Bean初始化的职责剥离出来

下一步我们将Bean的定义从Java代码迁移至XML文件

#### 将Bean的定义写入XML

建立一个XML文件在resources下
```xml
<?xml version="1.0" encoding="utf-8" ?>
<beans>
    <bean class = "com.michaelssss.TestBean" id="testBean">
        <attr name="word">attr1</attr>
    </bean>
</beans>
```

学习使用Dom4j的库

详情看XMLApplicationContext的实现以及BeanDefinitionInXMLLoader的实现

## 2018-11-12

重构文件结构，将类初始化功能从ApplicationContext玻璃到BeanUtils中

写的过程中就会遇到，我们parse不能在解析的时候就解析注入，所以初始化类与注入应该分开


### 接口描述

BeanDefinition 用于描述类是如何组装的

BeanDefinitionLoader 用于加载外部BeanDefinition描述并加载为BeanDefinition

ApplicationContext 用于外部使用获取组装后的Bean定义

至此，所有的IOC核心部件已经齐全