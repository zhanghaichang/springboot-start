# 数据校验之 validation
* 作为服务端开发，验证前端传入的参数的合法性是一个必不可少的步骤，但是验证参数是一个基本上是一个体力活，而且冗余代码繁多，也影响代码的可阅读性，所以有没有一个比较优雅的方式来解决这个问题？
* 这么简单的问题当然早就有大神遇到并且解决了，这一篇文章主要讲一下解决基于spring-boot的验证参数的比较好的方法：利用validator-api来进行验证参数。
* 在spring-boot-starter-web包里面有hibernate-validator包，它提供了一系列验证各种参数的方法，所以说spring-boot已经帮我们想好要怎么解决这个问题了。
***
#### 1. bean中添加标签
* 标签需要加在属性上，@NotBlank 标签含义
#### 2. Controller中开启验证
* 在Controller 中 请求参数上添加@Validated 标签开启验证
#### 3. resource 下新建错误信息配置文件
在resource 目录下新建提示信息配置文件“ValidationMessages.properties“
注意：名字必须为“ValidationMessages.properties“ 因为SpringBoot自动读取classpath中的ValidationMessages.properties里的错误信息
ValidationMessages.properties 文件的编码为ASCII。数据类型为 key value 。key“user.name.notBlank“为第一步 bean的标签 大括号里面对应message的值value 为提示信息 ，但是是ASCII 。（内容为“名字不能为空“）
#### 4. 自定义异常处理器，捕获错误信息
当验证不通过时会抛异常出来，异常的message 就是 ValidationMessages.properties 中配置的提示信息。此处定义异常处理器。捕获异常信息（因为验证不通过的项可能是多个所以统一捕获处理），并抛给前端。（此处是前后端分离开发）
#### 附上部分标签含义 

| 限制 | 说明 |
| :-- | :-- |
| @Null | 限制只能为null |
| @NotNull | 限制必须不为null |
| @AssertFalse | 限制必须为false |
| @AssertTrue | 限制必须为true |
| @DecimalMax(value) | 限制必须为一个不大于指定值的数字 |
| @DecimalMin(value) | 限制必须为一个不小于指定值的数字 |
| @Digits(integer,fraction) | 限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction |
| @Future | 限制必须是一个将来的日期 |
| @Max(value) | 限制必须为一个不大于指定值的数字 |
| @Min(value) | 限制必须为一个不小于指定值的数字 |
| @Past | 限制必须是一个过去的日期 |
| @Pattern(value) | 限制必须符合指定的正则表达式 |
| @Size(max,min) | 限制字符长度必须在min到max之间 |
| @Past | 验证注解的元素值（日期类型）比当前时间早 |
| @NotEmpty | 验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0） |
| @NotBlank | 验证注解的元素值不为空（不为null、去除首位空格后长度为0），不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的空格 |
| @Email | 验证注解的元素值是Email，也可以通过正则表达式和flag指定自定义的email格式 |

***
示例
```
 @Pattern(regexp="^[a-zA-Z0-9]+$",message="{account.username.space}")
 @Size(min=3,max=20,message="{account.username.size}")
 ```
 ***
 
```
200 ok  - 成功返回状态，对应，GET,PUT,PATCH,DELETE.
 201 created  - 成功创建。
 304 not modified   - HTTP缓存有效。
 400 bad request   - 请求格式错误。
 401 unauthorized   - 未授权。
 403 forbidden   - 鉴权成功，但是该用户没有权限。
 404 not found - 请求的资源不存在
 405 method not allowed - 该http方法不被允许。
 410 gone - 这个url对应的资源现在不可用。
 415 unsupported media type - 请求类型错误。
 422 unprocessable entity - 校验错误时用。
 429 too many request - 请求过多
 ```
