# RESTful API与单元测试
#### 1. 定义与特点
* 单元测试（unit testing）：是指对软件中的最小可测试单元进行检查和验证。
这个定义有点抽象，这里举几个单元测试的特性，大家感受一下：一般是一个函数配几个单元测试、单元测试不应该依赖外部系统、单元测试运行速度很快、单元测试不应该造成测试环境的脏数据、单元测试可以重复运行。
#### 2. 优点
* 单元测试使得我们可以放心修改、重构业务代码，而不用担心修改某处代码后带来的副作用。
单元测试可以帮助我们反思模块划分的合理性，如果一个单元测试写得逻辑非常复杂、或者说一个函数复杂到无法写单测，那就说明模块的抽象有问题。
单元测试使得系统具备更好的可维护性、具备更好的可读性；对于团队的新人来说，阅读系统代码可以从单元测试入手，一点点开始后熟悉系统的逻辑。

## Mockito
* Mocktio是一个非常易用的mock框架。开发者可以依靠Mockito提供的简洁的API写出漂亮的单元测试。

#### Mockito要点
* MockitoJUnitRunner：用于提供单元测试运行的容器环境
* Mock：用于模拟待测试模块中依赖的外部组件
* InjectMock：用于标识待测试组件
* org.mockito.Mockito.*：这个类里的方法可以用于指定Mock组件的预期行为，包括异常处理。
#### MockMvcRequestBuilders方法解析：
* perform：执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
* get:声明发送一个get请求的方法。MockHttpServletRequestBuilder get(String urlTemplate, Object... urlVariables)：根据uri模板和uri变量值得到一个GET请求方式的。另外提供了其他的请求的方法，如：post、put、delete等。
* param：添加request的参数，如上面发送请求的时候带上了了pcode = root的参数。假如使用需要发送json数据格式的时将不能使用这种方式，可见后面被@ResponseBody注解参数的解决方法
* andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确（对返回的数据进行的判断）；
* andDo：添加ResultHandler结果处理器，比如调试时打印结果到控制台（对返回的数据进行的判断）；
* andReturn：最后返回相应的MvcResult；然后进行自定义验证/进行下一步的异步处理（对返回的数据进行的判断）；
* [更多详情](https://www.petrikainulainen.net/spring-mvc-test-tutorial/)  
## Spock
* Spock框架是基于Groovy语言的测试框架，Groovy与Java具备良好的互操作性，因此可以在Spring Boot项目中使用该框架写优雅、高效以及DSL化的测试用例。Spock通过@RunWith注解与JUnit框架协同使用，另外，Spock也可以和Mockito(Spring Boot应用的测试——Mockito)协同使用。

```maven
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-test</artifactId>
   <scope>test</scope>
</dependency>
<dependency>
   <groupId>org.spockframework</groupId>
   <artifactId>spock-core</artifactId>
   <scope>test</scope></dependency>
<dependency>
   <groupId>org.spockframework</groupId>
   <artifactId>spock-spring</artifactId>
   <scope>test</scope>
</dependency>
```

