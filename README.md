# 从头开始写Spring

## 2018-11-10

首先我们从平时使用Spring的场景中提取最基本的操作

1. 我们通过Spring管理实例

2. 我们通过Spring来创建实例

3. 我们通过Spring来完成依赖注入

那我们的从以上三点来设计一个我们自己的Spring版本


## 2018-11-12

### 接口描述

BeanDefinition 用于描述类是如何组装的

BeanDefinitionLoader 用于加载外部BeanDefinition描述并加载为BeanDefinition

ApplicationContext 用于确定是用什么外部资源来装配BeanDefinition

BeanFactory 用于根据BeanDefinition创建Bean

至此，所有的IOC核心部件已经齐全
