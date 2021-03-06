> 参考 
> 《手撸spring》 ———— bugstack 虫洞栈
> 
> b站视频 2021吃透这些Java手写
> 
> 菜鸟教程

> 对初步完成的spring容器进行重构，定义接口，接口继承接口，接口由抽象类实现，类继承的接实现接口方法。为了让核心逻辑层和业务封装层做好隔离，当业务变化时只需要在业务层完成装配而底层的核心逻辑服务不需要频繁变化；解耦，方便实现复杂的功能(如context服务包和beanfactory核心包，如何对接，就是继承了beanfactory核心包的增强接口，新增beanfactorypostprocess功能时不需要改动核心包)
> 
> 在全局包下定义的接口是对外暴露的业务接口，隐藏了内部逻辑接口，可作为在此基础上开发的进一步功能的底层接口
> 
> 模板设计模式，一期的容器看起来混乱，耦合严重，重新设计逐步添加功能,设计模式的包装让对外的服务调用更加简单
> 
> 在io处理时的组合思想 装饰器设计模式加载xml
> 
> 重构点4 beanfactorypostprocess和beanpostprocess实现细节尚未理解，需要重新学习

# 一 Spring相关知识点
![avator](/assets/spring生命周期.png)
## 1 springBean容器
### 是什么
spring包含并管理应用对象的配置和生命周期，在这个意义上它是一种用于承载对象的容器，可以配置每个Bean对象是如何被创建的。
如果一个Bean对象交给Spring容器管理，那么这个Bean对象就应该以类似零件的方式被拆解后存放到Bean的定义里，相当于一种把对象解耦的操作。可以让spring更加容易的管理，就行处理循环依赖那样。
### 设计
凡是可以存放数据的具体数据结构实现，都可以称之为容器。如LinkedList等，但在spring容器的场景下，我们需要一种可以用于存放和名称索引式的数据结构，HashMap是最合适不过的。

> HashMap是一种基于扰动函数，负载因子，红黑树转换等技术内容，形成的拉链寻址的数据结构，能让数据更加散列的分布在哈希桶以及碰撞时形成的链表和红黑树上

此外一个简单的spring bean容器实现，还需要Bean的定义，注册，获取三个基本步骤。
- 定义:BeanDefinition
- 注册:相当于把数据放到hashMap中
- 获取:通过key获取bean对象

## 2 模板模式(见二的详解)
在模板模式（Template Pattern）中，一个抽象类公开定义了执行它的方法的方式/模板。它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。这种类型的设计模式属于行为型模式。

**意图**：定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。

**主要解决**：一些方法通用，却在每一个子类都重新写了这一方法。

**何时使用**：有一些通用的方法。

**如何解决**：将这些通用算法抽象出来。

**关键代码**：在抽象类实现，其他步骤在子类实现。

**应用实例**： 1、在造房子的时候，地基、走线、水管都一样，只有在建筑的后期才有加壁橱加栅栏等差异。 2、西游记里面菩萨定好的 81 难，这就是一个顶层的逻辑骨架。 3、spring 中对 Hibernate 的支持，将一些已经定好的方法封装起来，比如开启事务、获取 Session、关闭 Session 等，程序员不重复写那些已经规范好的代码，直接丢一个实体就可以保存。

**优点**： 1、封装不变部分，扩展可变部分。 2、提取公共代码，便于维护。 3、行为由父类控制，子类实现。

**缺点**：每一个不同的实现都需要一个子类来实现，导致类的个数增加，使得系统更加庞大。

**使用场景**： 1、有多个子类共有的方法，且逻辑相同。 2、重要的、复杂的方法，可以考虑作为模板方法。

**注意事项**：为防止恶意操作，一般模板方法都加上 final 关键词。

> 问 为什么模板模式的模板不用接口而是使用抽象类
> 因为模板模式暴露的钩子是生命周期（或处理流程）的一部分。
> 很显然接口并不能让你先做好一部分事情，然后其中一些事情（判断？持久化？从一个外部数据结构转换为模板所需的数据结构？诸如此类）留空给别人去做。
> 接口只是告诉别人我可以做什么事，但是我不知道怎么做，怎么做到则是由接口的实现类定义。
> 抽象类是我搞定大部分了，你来告诉我一些细节要怎么做就ok了。

## 3 包装(装饰)模式(见二的详解)

#####  spring生命周期
1. 创建
   class（UserService.class）--> 配置类--> 实例化--> 对象--> 属性填充（依赖注入）--> 初始化（afterPropertiesSet）--> AOP--> 代理对象--> Bean对象
- 加载和初始化是俩码事，在scan方法时使用classLoad加载获取注释，在scan后或creat时初始化，此时可以使用Initialization接口做一些设置（类比vue的声明周期）
- AOP
2. 销毁

# 二 Spring部分实现
## 一期实现 在Application包下
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

##### 3. spring相关接口实现
1. BeanNameAware回调，bean获取自己在bean工厂中的beanName。让其意识到自己在bean中的存在以调用其他bean，在bean单例第一次扫描或者多例获取时时创建
2. InitializingBean初始化，在bean初始话时对bean进行一些操作
3. BeanPostProcessor接口（bean的后置处理器），在bean初始化前和初始化后对bean进行一些操作
- 程序猿自定义，实现BeanPostProcessor接口
- 扫描时判断，如果是BeanPostProcessor接口的实现就加入List
- 最后在creatBean时执行List中的全部BeanPostProcessor
4. AOP实现，在BeanPostProcessor的接口上对类拓展工程

###### 一些心得体会：
1.注释相当于为类添加了一些属性和属性值，便于操作；同时也有2的考虑
2.接口或注释都可以为类提供一些标识，在spring对类自动操作时可以通过这些标识执行特定的操作(如InitializingBean，BeanPostProcessor等)
3.基本都在scan时把特殊需求的类加入list，在creatBean时针对特对的类操作
4.缓存池，一般使用List<Map>，在程序运行期间存在

## 二期重构 在spring包下
### 重构点1 模板设计模式实现bean工厂
![avator](/assets/模板模式.png)
- Bean工厂的接口由抽象类AbstractBeanFactory实现，使用模板模式统一收口通用核心方法的调用逻辑和标准定义，也就很好的控制了后续的实现者不要关心调用逻辑，按照统一方式执行，只要关心具体方法的逻辑实现即可。
- 几乎所有的程序都离不开接口，抽象类，实现，继承，而这些不同特性的类的使用就可以非常好的隔离开类的功能职责和范围。
- **如在spring中，AbstractBeanFactory为模板类，规定了getBean，createBean，getBeanDefinition三个方法，还可以使用继承的单例注册bean方法。getBean在此实现单例的情况，其他情况下的操作在其子类中实现(AbstractAutowireCapable,DefaultListableFactory)**
- DefaultListableFactory(核心实现类)通过继承获得了获取bean(上溯到AbstractBeanFactory的getBean)的能力，通过实现接口获得了注册bean的能力

### 重构点2 
![avator](/assets/实例化结构.png)
- 实例方法1： CGLIB代理主要通过对字节码的操作，为对象引入间接级别，以控制对象的访问。我们知道Java中有一个动态代理也是做这个事情的，那我们为什么不直接使用Java动态代理，而要使用CGLIB呢？答案是CGLIB相比于JDK动态代理更加强大，JDK动态代理虽然简单易用，但是其有一个致命缺陷是，只能对接口进行代理。如果要代理的类为一个普通类、没有接口，那么Java动态代理就没法使用了。关于Java动态代理，可以参者这里Java动态代理分析
- 实例方法2： Java自带的方法，反射
- 实例化都是在getBean的时候进行，实例化时传入name和参数，在AACB类中获取beanDefination再实例化

### 重构点3 
![avator](/assets/xml结构图.png)
- 添加xml文件属性注入，在createBean的时候，位于Spring框架核心包下的IO实现内容
- **接口管定义，抽象类处理非接口功能外的注册 Bean 组件填充，最终实现类即可只关心具体的业务实现**
- 本章节为了能把 Bean 的定义、注册和初始化交给 Spring.xml 配置化处理，那么就需要实现两大块内容，分别是：资源加载器、xml 资源处理类，实现过程主要以对接口 Resource、ResourceLoader 的实现，而另外BeanDefinitionReader 接口则是对资源的具体使用，将配置信息注册到Spring 容器中去。
- 包装模式设计加载xml，增强接口，先添加注册到beanDefinition中的操作，再实现加载
- 读取资源时的设计(core.io包)为策略模式
> 具体见原书58页
> https://blog.csdn.net/m0_38075425/article/details/81627349 为什么classpth下要用类加载器加载资源与类加载机制
> 抽象类可以只实现接口的某些方法(如工具方法)，把主要方法(业务方法)留到以后实现(或者使用装饰器设计模式)

### 重构点4 应用上下文 上下文，上下文代表了程序当下所运行的环境，联系你整个app的生命周期与资源调用，是程序可以访问到的所有资源的总和，资源可以是一个变量，也可以是一个对象的引用。(差不多封装了用户可以访问的资源，面向用户，再如Netty的ctx上下文)
- 原书P73页
- BeanFactoryPostProocessor 在Bean对象注册后(即BeanDifinition注册后)但未实例化之前，对即BeanDifinition进行修改  **其实现在context的support包下，在xml解析时即实现**
- BeanPostProocessor 在对象实例化之后修改Bean对象或者替换Bean对象 **其实现在AutowireCapableBeanFactory包下，进行createBean的前置和后置操作**

- context服务包下，先关注ApplicationContext接口，有哪些主要功能；AbstractApplication核心类实现主要功能，实现主要方法，方法依赖的方法或工具类或不同情况下的实现或前置后置操作在继承类中处理；AbstractApplicationContext继承ListableBeanFactory继承BeanFactory，获得beanFactory相关方法，但是和BeanFactory区本身的各种方法隔离，只关注功能，解耦在易于理解的同时也方便下一步的拓展和实现

**实现接口的具体方式还没有看懂**  

> Spring装配Bean的过程 在AbstractAutowireCapableBeanFactory中有所体现
> 此类是 AbstractBeanFactory 的子类，AbstractBeanFactory是模板抽象类，其预留的creatBean模板方法(createBean时的实例化，属性注入，前置后置处理)主要在此类中实现，此类是一个装饰器类
> 注意bean的前置和后置操作均在属性注入之后
> **收获:如果不使用模板设计方式，各个复杂的功能模板杂在一起根本看不懂**
> **收获:关于对外暴露接口，将xml的读取和加载融合到spring启动生命周期中，不需要用户手动获取bean工厂后注入，不对外暴露xml处理类的接口，提高了用户体验和安全性**
>
> 1. 实例化;
>
> 2. 设置属性值;
>
> 3. 如果实现了BeanNameAware接口,调用setBeanName设置Bean的ID或者Name;
>
> 4. 如果实现BeanFactoryAware接口,调用setBeanFactory 设置BeanFactory;
>
> 5. 如果实现ApplicationContextAware,调用setApplicationContext设置ApplicationContext
>
> 6. 调用BeanPostProcessor的预先初始化方法;
>
> 7. 调用InitializingBean的afterPropertiesSet()方法;
>
> 8. 调用定制init-method方法；
>
> 9. 调用BeanPostProcessor的后初始化方法;
>   ————————————————
>   版权声明：本文为CSDN博主「Cyandev」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
>   原文链接：https://blog.csdn.net/weixin_29572661/article/details/114244993

### 重构点5 初始化与销毁方法
![avator](/assets/应用上下文与创建销毁方法和钩子.png)
InitializingBean,DisposableBean 两个接口方法常用，常用这两个方法来做一些参数的初始化和销毁操作，如接口暴露，数据库数据读取，配置文件加载等

**初始化与销毁方法与post方法的区别**
- 储存位置不同 初始化与销毁方法在beanDefinition中储存 beanpost方法在AbstractBeanFactory(模板类)中维护一个post方法池(单例模式？？？) (为什么这么做，有什么好处？)
  - 好处的初步理解，在xml的bean配置中即可设置？  


# 三 Dubbo部分实现


#### dubbo原理
- 生产者消费者模式

#### 代码流程
- 服务提供者
  - 配置内嵌tomcat
  - tomcat接收request后先走配置的servlet
  - servlet处理类中配置了handler类处理逻辑
  - handler类中对servlet类中的参数（接口名，版本号，方法名，参数类型，参数名）进行解析
  - 从解析的接口名中找到接口实现类

#### 部分问题记录
- Serializable接口，方便数据远程传输，序列化
  https://baijiahao.baidu.com/s?id=1633305649182361563&wfr=spider&for=pc
  实际为空接口，仅为jvm编译时提供标识（标识接口）
  
- 接口暴露 本地注册接口实现类（hashmap缓存），方便在传入方法时寻找对应的接口
#### 未解决的问题
- 序列化与反序列话具体的过程

# 四 tomcat部分实现

> 1.entrySet映射怎么用
> 2.https://blog.csdn.net/weixin_42181686/article/details/115074669 反射有什么好处
> 3.反射创建实例与bean初始化之间的关系 
> 4.向上转型，向下转型，动态代理的方法
> 5.抽象类和接口的区别 抽象类是把大部分的事情做好并暴露出来，只有细节等着下面的继承类实现
> 6.toArray()，JAVA函数，当使用ArrayList时，有时想获得一个实际的数组，这个数组包含了列表的内容。可以通过调用方法toArray()来实现。
> 7.看源码的方法(spring设计的妙处):从模板方法开始，先忽略细节把握脉络；看核心实现类和主要接口，看其实现了哪些功能；具体到细节，时间顺序，正序或倒叙，看一部分时忽略另一部分的实现，只关注功能，相互隔离
> 如context服务包下，先关注ApplicationContext接口，有哪些主要功能；AbstractApplication核心类实现主要功能，实现主要方法，方法依赖的方法或工具类或不同情况下的实现或前置后置操作在继承类中处理；AbstractApplicationContext继承ListableBeanFactory继承BeanFactory，获得beanFactory相关方法，但是和BeanFactory区本身的各种方法隔离，只关注功能，解耦在易于理解的同时也方便下一步的拓展和实现