# Swagger 介绍
* [Swagger](https://swagger.io/) 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。总体目标是使客户端和文件系统作为服务器以同样的速度来更新。文件的方法，参数和模型紧密集成到服务器端的代码，允许API来始终保持同步。Swagger 让部署管理和使用功能强大的API从未如此简单。
* Swagger™的目标是为REST APIs 定义一个标准的，与语言无关的接口，使人和计算机在看不到源码或者看不到文档或者不能通过网络流量检测的情况下能发现和理解各种服务的功能。当服务通过Swagger定义，消费者就能与远程的服务互动通过少量的实现逻辑。类似于低级编程接口，Swagger去掉了调用服务时的很多猜测。 
***
###### 优点
* 前后端分离开发
* API文档非常明确
* 测试的时候不需要再使用URL输入浏览器的方式来访问Controller
* 传统的输入URL的测试方式对于post请求的传参比较麻烦（当然，可以使用postman这样的浏览器插件）
* spring-boot与swagger的集成简单的一逼
###### 常用的几个注解
* @Api：用在类上，说明该类的作用
* @ApiOperation：用在方法上，说明方法的作用
* @ApiImplicitParams：用在方法上包含一组参数说明
* @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
    * paramType：参数放在哪个地方
        * header-->请求参数的获取：@RequestHeader
        * query-->请求参数的获取：@RequestParam
        * path（用于restful接口）-->请求参数的获取：@PathVariable
        * body（不常用）
        * form（不常用）
    * name：参数名
    * dataType：参数类型
    * required：参数是否必须传
    * value：参数的意思
    * defaultValue：参数的默认值
* @ApiResponses：用于表示一组响应
* @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
    * code：数字，例如400
    * message：信息，例如"请求参数没填好"
    * response：抛出异常的类
* @ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
    * @ApiModelProperty：描述一个model的属性
* 在pom.xml中加入Swagger2的依赖
```maven
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.2.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.2.2</version>
</dependency>
```
* [swagger启动页面](http://localhost:8001/swagger-ui.html)
* [详细介绍](http://blog.didispace.com/springbootswagger2/)
* [github](https://github.com/swagger-api/swagger-core/wiki/Annotations#apimodel)
