# Spring Data Rest 

是基于 Spring Data repositories，分析实体之间的关系。为我们生成Hypermedia API(HATEOAS)风格的Http Restful API接口。

**它所具有的特性，比如：**

* 根据model，生成HAL风格的restful API
* 根据model，维护实体之间的关系
* 支持分页

诸多的特性，[官方文档](http://docs.spring.io/spring-data/rest/docs/2.6.3.RELEASE/reference/html/ "官方文档")都会有提及。这里我们着重关注在Spring Data Rest中基于JPA维护实体之间关系。

## HAL Browser 使用

HAL-browser 是基于hal+json的media type的API浏览器，Spring Data Rest 提供了集成，pom文件中加个这个。

```maven
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-rest-hal-browser</artifactId>
</dependency>
```

启动我们的程序，打开浏览器 [地址](https://localhost:8433/api/v1/browser/index.html#/api/v1)

> api/v1 是我指定的接口前缀，通过配置项 spring.data.rest.base-path 指定