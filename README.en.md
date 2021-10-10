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

##### spring相关接口实现
1. BeanNameAware回调，bean获取自己在bean工厂中的beanName。让其意识到自己在bean中的存在以调用其他bean，在bean单例第一次扫描或者多例获取时时创建
2. InitializingBean初始化，在bean初始话时对bean进行一些操作
3. BeanPostProcessor接口（bean的后置处理器），在bean初始化前和初始化后对bean进行一些操作
- 程序猿自定义，实现BeanPostProcessor接口
- 扫描时判断，如果是BeanPostProcessor接口的实现就加入List
- 最后在creatBean时执行List中的全部BeanPostProcessor
4. AOP实现，在BeanPostProcessor的接口上对类拓展工程

>相关说明
> 注释 自我感觉就是为类添加了一个属性，注释名就是属性名，注释值就是属性值
> @Retention(RetentionPolicy.RUNTIME) 注释 规定注释存在的生命周期
> @Target 说明了Annotation所修饰的对象范围
>

###### 一些心得体会：
1.注释相当于为类添加了一些属性和属性值，便于操作；同时也有2的考虑
2.接口或注释都可以为类提供一些标识，在spring对类自动操作时可以通过这些标识执行特定的操作(如InitializingBean，BeanPostProcessor等)
3.基本都在scan时把特殊需求的类加入list，在creatBean时针对特对的类操作
4.

###### 疑问：
1.entrySet映射怎么用
2.https://blog.csdn.net/weixin_42181686/article/details/115074669 反射有什么好处
3.反射创建实例与bean初始化之间的关系
4.向上转型，向下转型，动态代理的方法