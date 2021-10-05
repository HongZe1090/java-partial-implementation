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
###### BeanDefinition相当于类与属性的管理池，全部bean与其属性均在次托管，singletonObjects是单例bean管理池
- 构造spring容器，传入配置类，根据配置类解析
  - 先判断是否有spring的config注解，如果没有不需要解析
- 根据注解获取需要扫描的文件(包)
- 根据上述包，先使用classPath(target根目录下)的类加载加载上述类
  - 类加载器加载的是类名，所以要先把类的路径处理成类名，加载时才会处理注释
  - 如果有spring相关的注解，表明这个类需要被spring托管
  - 则调用BeanDefinition类储存类的各个属性,包括类加载器的返回值和scope
  - 将BeanDefinition类注册到beanDefinitionMap中，留待取用
- 遍历BeanDefinitionMap,如果有单例模式，则创建Bean实例并储存到singletonMap中留待取用

##### 2 getBean()流程
- 根据className遍历BeanDefinition
- 如果有则判断其的scope
- 若为单例模式，从singletonMap中取用（在加载springContext时加载）

>相关说明
> 注释 自我感觉就是为类添加了一个属性，注释名就是属性名，注释值就是属性值
> @Retention(RetentionPolicy.RUNTIME) 注释 规定注释存在的生命周期
> @Target 说明了Annotation所修饰的对象范围
> 

###### 疑问：
1.entrySet映射怎么用
2.https://blog.csdn.net/weixin_42181686/article/details/115074669 反射有什么好处