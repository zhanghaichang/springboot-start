# 单元测试利器Mockito框架

`Mockito` 是当前最流行的 **单元测试** Mock 框架。采用 Mock 框架，我们可以 虚拟 出一个 外部依赖，降低测试 组件 之间的 耦合度，只注重代码的 流程与结果，真正地实现测试目的。


## 什么是Mock

Mock 的中文译为仿制的，模拟的，虚假的。对于测试框架来说，即构造出一个模拟/虚假的对象，使我们的测试能顺利进行下去。

Mock 测试就是在测试过程中，对于某些 不容易构造（如 HttpServletRequest 必须在 Servlet 容器中才能构造出来）或者不容易获取 比较复杂 的对象（如 JDBC 中的 ResultSet 对象），用一个 虚拟 的对象（Mock 对象）来创建，以便测试方法。

## 为什么使用Mock测试

单元测试 是为了验证我们的代码运行正确性，我们注重的是代码的流程以及结果的正确与否。

对比真实运行代码，可能其中有一些 外部依赖 的构建步骤相对麻烦，如果我们还是按照真实代码的构建规则构造出外部依赖，会大大增加单元测试的工作，代码也会参杂太多非测试部分的内容，测试用例显得复杂难懂。

采用 Mock 框架，我们可以 虚拟 出一个 外部依赖，只注重代码的 流程与结果，真正地实现测试目的。

## Mock测试框架的好处

* 可以很简单的虚拟出一个复杂对象（比如虚拟出一个接口的实现类）；

* 可以配置 mock 对象的行为；

* 可以使测试用例只注重测试流程与结果；

* 减少外部类、系统和依赖给单元测试带来的耦合。

**maven引入**

```maven
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-all</artifactId>
    <version>1.10.19</version>
    <scope>test</scope>
</dependency>
```

[参考文章](https://juejin.im/post/5b3a23cd6fb9a024e53ce223?utm_source=gold_browser_extension#heading-18)