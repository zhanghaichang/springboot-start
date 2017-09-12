# Restful API统一异常处理
* 我们在做Web应用的时候，请求处理过程中发生错误是非常常见的情况。Spring Boot提供了一个默认的映射：/error，当处理中抛出异常之后，会转到该请求中处理，并且该请求有一个全局的错误页面用来展示异常内容。

#### @ControllerAdvice 和 @ExceptionHandler 的区别
* ExceptionHandler, 方法注解, 作用于 Controller 级别. ExceptionHandler 注解为一个 Controler 定义一个异常处理器.
ControllerAdvice, 类注解, 作用于 整个 Spring 工程. ControllerAdvice 注解定义了一个全局的异常处理器.
需要注意的是, ExceptionHandler 的优先级比 ControllerAdvice 高, 即 Controller 抛出的异常如果既可以让 ExceptionHandler 标注的方法处理, 又可以让 ControllerAdvice 标注的类中的方法处理, 则优先让 ExceptionHandler 标注的方法处理.

# AOP统一处理Web请求日志
* AOP为Aspect Oriented Programming的缩写，意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是Spring框架中的一个重要内容，它通过对既有程序定义一个切入点，然后在其前后切入不同的执行内容，比如常见的有：打开数据库连接/关闭数据库连接、打开事务/关闭事务、记录日志等。基于AOP不会破坏原来程序逻辑，因此它可以很好的对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。 
pom.xml
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```
* 在完成了引入AOP依赖包后，一般来说并不需要去做其他配置。也许在Spring中使用过注解配置方式的人会问是否需要在程序主类中增加@EnableAspectJAutoProxy来启用，实际并不需要。

* 可以看下面关于AOP的默认配置属性，其中spring.aop.auto属性默认是开启的，也就是说只要引入了AOP依赖后，默认已经增加了@EnableAspectJAutoProxy。
```
# AOP
spring.aop.auto=true # Add @EnableAspectJAutoProxy.
spring.aop.proxy-target-class=false # Whether subclass-based (CGLIB) proxies are to be created (true) as
 opposed to standard Java interface-based proxies (false).
```
而当我们需要使用CGLIB来实现AOP的时候，需要配置spring.aop.proxy-target-class=true，不然默认使用的是标准Java的实现。

#### 实现Web层的日志切面

实现AOP的切面主要有以下几个要素：

* 使用@Aspect注解将一个java类定义为切面类
* 使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
* 根据需要在切入点不同位置的切入内容
    * 使用@Before在切入点开始处切入内容
    * 使用@After在切入点结尾处切入内容
    * 使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
    * 使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
    * 使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
    
#### 优化：AOP切面的优先级

由于通过AOP实现，程序得到了很好的解耦，但是也会带来一些问题，比如：我们可能会对Web层做多个切面，校验用户，校验头信息等等，这个时候经常会碰到切面的处理顺序问题。

所以，我们需要定义每个切面的优先级，我们需要@Order(i)注解来标识切面的优先级。i的值越小，优先级越高。假设我们还有一个切面是CheckNameAspect用来校验name必须为didi，我们为其设置@Order(10)，而上文中WebLogAspect设置为@Order(5)，所以WebLogAspect有更高的优先级，这个时候执行顺序是这样的：

* 在@Before中优先执行@Order(5)的内容，再执行@Order(10)的内容
* 在@After和@AfterReturning中优先执行@Order(10)的内容，再执行@Order(5)的内容
所以我们可以这样子总结：

* 在切入点前的操作，按order的值由小到大执行
* 在切入点后的操作，按order的值由大到小执行