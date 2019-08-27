# Restful API统一异常处理
* Spring Boot Webflux 就是基于 Reactor 实现的。Spring Boot 2.0 包括一个新的 spring-webflux 模块。该模块包含对响应式 HTTP 和 WebSocket 客户端的支持，以及对 REST，HTML 和 WebSocket 交互等程序的支持。一般来说，Spring MVC 用于同步处理，Spring Webflux 用于异步处理。

* Spring Boot Webflux 有两种编程模型实现，一种类似 Spring MVC 注解方式，另一种是使用其功能性端点方式。注解的会在第二篇文章讲到，下面快速入门用 Spring Webflux 功能性方式实现。


## Spring Boot 2.0 WebFlux 组件
* Spring Boot WebFlux 官方提供了很多 Starter 组件，每个模块会有多种技术实现选型支持，来实现各种复杂的业务需求：

* Web：Spring WebFlux
* 模板引擎：Thymeleaf
* 存储：Redis、MongoDB、Cassandra。不支持 MySQL
* 内嵌容器：Tomcat、Jetty、Undertow

#### 常用的 Spring Boot 2.0 WebFlux 生产的特性如下：

* 响应式 API
* 编程模型
* 适用性
* 内嵌容器
* Starter 组件
* 还有对日志、Web、消息、测试及扩展等支持。