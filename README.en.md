# Spring部分实现

#### 实现内容包括
- spring启动及扫描流程 
- getBean（）流程
- Bean生命周期流程
- 依赖注入流程
- BeanPostProcessor机制
- Aop机制
- getter，setter注释封装
  
##### 1 spring启动及扫描流程
- 构造spring容器，传入配置类，根据配置类解析
  - 先判断是否有spring的config注解，如果没有不需要解析
- 根据注解获取需要扫描的文件
- 根据有spring注解的类，扫描文件下各个类
  - 有注解，表明这是一个类
-

##### 2 getBean()流程

>相关说明
> @Retention(RetentionPolicy.RUNTIME) 注释 规定注释存在的生命周期
> @Target 说明了Annotation所修饰的对象范围