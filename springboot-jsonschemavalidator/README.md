# 数据校验之 validation
* 作为服务端开发，验证前端传入的参数的合法性是一个必不可少的步骤，但是验证参数是一个基本上是一个体力活，而且冗余代码繁多，也影响代码的可阅读性，所以有没有一个比较优雅的方式来解决这个问题？
* 这么简单的问题当然早就有大神遇到并且解决了，这一篇文章主要讲一下解决基于spring-boot的验证参数的比较好的方法：利用json-schema-validator来进行验证参数。

Maven
```xml
<dependency>
    <groupId>com.github.java-json-tools</groupId>
    <artifactId>json-schema-validator</artifactId>
    <version>2.2.10</version>
</dependency>
```
* [官方json-schema-validator](https://github.com/java-json-tools/json-schema-validator)

* [swagger ui](http://localhost:8001/swagger-ui.html#/user-controller)

***
#### 1. JsonSchemaValidator.java

```json
{
  "email": "string",
  "enabled": 0,
  "password": "string",
  "qq": 12345,
  "realname": "string",
  "tel": 123456789,
  "username": "string",
  "usertype": 1
}
```

利用json schema文件/templates/UserInfo.json 验证字段信息

[json schema 在线生成](http://json-schema-validator.herokuapp.com/jjschema.jsp)

## 针对请求报文较大而且不规则的报文可以尝试一下这个验证框架


