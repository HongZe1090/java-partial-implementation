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
- 根据注解获取需要扫描的文件(包)
- 根据上述包，先使用classPath(target根目录下)的类加载加载上述类
  - 类加载器加载的是类名，所以要先把类的路径处理成类名，加载时才会处理注释
  - 如果有spring相关的注解，表明这个类需要被spring托管
  - 则调用BeanDefinition类储存类的各个属性
-

##### 2 getBean()流程

>相关说明
> 注释 自我感觉就是为类添加了一个属性，注释名就是属性名，注释值就是属性值
> @Retention(RetentionPolicy.RUNTIME) 注释 规定注释存在的生命周期
> @Target 说明了Annotation所修饰的对象范围