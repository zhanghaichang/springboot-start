#  Spring Boot Freemarker

>FreeMarker是一款模板引擎： 即一种基于模板和要改变的数据，	并用来生成输出文本（HTML网页、电子邮件、配置文件、源代码等）的通用工具。	它不是面向最终用户的，而是一个Java类库，是一款程序员可以嵌入他们所开发产品的组件。

* 在springMVC中：它代表着view层组件

*  为什么使用freemarker：简单容易学、逻辑分明

* freemarker优点：它不依赖servlet、网络或者web环境

首先，pom.xml中导入freemarker的依赖：
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```
在application.properties(或yml)配置文件中加入freemarker相关配置：

```
#    freemarker静态资源配置

#       设定ftl文件路径
spring.freemarker.tempalte-loader-path=classpath:/templates
#        关闭缓存，及时刷新，上线生产环境需要修改为true
spring.freemarker.cache=false
spring.freemarker.charset=UTF-8
spring.freemarker.check-template-location=true
spring.freemarker.content-type=text/html
spring.freemarker.expose-request-attributes=true
spring.freemarker.expose-session-attributes=true
spring.freemarker.request-context-attribute=request
spring.freemarker.suffix=.ftl
```

这里指定了freemarker文件的路径是classpath/templates，在resources文件夹下的templates新建freemarker文件夹，并且在其中新建index.ftl（上面配置文件中已经指定了freemarker模板的文件后缀为ftl）：

```
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8"/>
    <title></title>
</head>
<body>
FreeMarker模板引擎
<h1>${resource.name}</h1>
<h1>${resource.website}</h1>
<h1>${resource.language}</h1>
</body>
</html>

```