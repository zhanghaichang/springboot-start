# springboot-data-jpa-h2 多数据源
* Spring-data-jpa的基本介绍：JPA诞生的缘由是为了整合第三方ORM框架，建立一种标准的方式，
百度百科说是JDK为了实现ORM的天下归一，目前也是在按照这个方向发展，但是还没能完全实现。
在ORM框架中，Hibernate是一支很大的部队，使用很广泛，也很方便，能力也很强，
同时Hibernate也是和JPA整合的比较良好，我们可以认为JPA是标准，事实上也是，JPA几乎都是接口，实现都是Hibernate在做，
宏观上面看，在JPA的统一之下Hibernate很良好的运行。

***
#### Spring Data JPA有什么
主要来看看Spring Data JPA提供的接口，也是Spring Data JPA的核心概念：
* 1：Repository：最顶层的接口，是一个空的接口，目的是为了统一所有Repository的类型，且能让组件扫描的时候自动识别。
* 2：CrudRepository ：是Repository的子接口，提供CRUD的功能
* 3：PagingAndSortingRepository：是CrudRepository的子接口，添加分页和排序的功能
* 4：JpaRepository：是PagingAndSortingRepository的子接口，增加了一些实用的功能，比如：批量操作等。
* 5：JpaSpecificationExecutor：用来做负责查询的接口
* 6：Specification：是Spring Data JPA提供的一个查询规范，要做复杂的查询，只需围绕这个规范来设置查询条件即可
```xml
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-jpa</artifactId>
    <version>2.0.0.RC2</version>
</dependency>
```

