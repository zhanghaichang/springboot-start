# Spring Data Rest 

是基于 Spring Data repositories，分析实体之间的关系。为我们生成Hypermedia API(HATEOAS)风格的Http Restful API接口。

**它所具有的特性，比如：**

* 根据model，生成HAL风格的restful API
* 根据model，维护实体之间的关系
* 支持分页

此时使用GET访问http://localhost:8080/persons/search/nameStartsWith?name=kevin

可实现查询操作

> http://localhost:8080/persons/?page=1&size=2　　　　分页查询，page-1即第2页，size=2即每页数量为2

> http://localhost:8080/persons/?sort=age,desc　　　　  排序，即按照age属性倒序

 POST请求

> http://localhost:8080/persons ,并将数据放到请求体中　　　　　　保存

> http://localhost:8080/persons/21,并将数据放到请求体中　　　　  更新

DELETE请求

> http://localhost:8080/persons/21    　　　　　　　　　　　　　 删除

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

> api/v1 是我指定的接口前缀，通过application.properties配置项 spring.data.rest.base-path 指定
