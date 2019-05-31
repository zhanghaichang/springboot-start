# SpringBoot 2.X ShardingSphere分库分表

[官网地址](官网地址: https://shardingsphere.apache.org/)

* ShardingSphere是一套开源的分布式数据库中间件解决方案组成的生态圈，它由Sharding-JDBC、Sharding-Proxy和Sharding-Sidecar（计划中）这3款相互独立的产品组成。他们均提供标准化的数据分片、分布式事务和数据库治理功能，可适用于如Java同构、异构语言、容器、云原生等各种多样化的应用场景。


* ShardingSphere定位为关系型数据库中间件，旨在充分合理地在分布式的场景下利用关系型数据库的计算和存储能力，而并非实现一个全新的关系型数据库。它与NoSQL和NewSQL是并存而非互斥的关系。NoSQL和NewSQL作为新技术探索的前沿，放眼未来，拥抱变化，是非常值得推荐的。反之，也可以用另一种思路看待问题，放眼未来，关注不变的东西，进而抓住事物本质。关系型数据库当今依然占有巨大市场，是各个公司核心业务的基石，未来也难于撼动，我们目前阶段更加关注在原有基础上的增量，而非颠覆。

* sharding-jdbc 定位为轻量级Java框架，在Java的JDBC层提供的额外服务。它使用客户端直连数据库，以jar包形式提供服务，无需额外部署和依赖，可理解为增强版的JDBC驱动，完全兼容JDBC和各种ORM框架。

* 适用于任何基于Java的ORM框架，如：JPA, Hibernate, Mybatis, Spring JDBC Template或直接使用JDBC。基于任何第三方的数据库连接池，如：DBCP, C3P0, BoneCP, Druid, HikariCP等。支持任意实现JDBC规范的数据库。目前支持MySQL，Oracle，SQLServer和PostgreSQL。

本项目基于 Spring Boot 使用sharding-sphere + Mybatis-Plus 实现分库分表
```shell
ds0

  ├── user_0

  └── user_1

ds1

  ├── user_0

  └── user_1
```
### application.properties (重点)

```properties
# 数据源 ds0,ds1
sharding.jdbc.datasource.names=ds0,ds1
# 第一个数据库
sharding.jdbc.datasource.ds0.type=com.zaxxer.hikari.HikariDataSource
sharding.jdbc.datasource.ds0.driver-class-name=com.mysql.jdbc.Driver
sharding.jdbc.datasource.ds0.jdbc-url=jdbc:mysql://localhost:3306/ds0?characterEncoding=utf-8
sharding.jdbc.datasource.ds0.username=root
sharding.jdbc.datasource.ds0.password=root

# 第二个数据库
sharding.jdbc.datasource.ds1.type=com.zaxxer.hikari.HikariDataSource
sharding.jdbc.datasource.ds1.driver-class-name=com.mysql.jdbc.Driver
sharding.jdbc.datasource.ds1.jdbc-url=jdbc:mysql://localhost:3306/ds1?characterEncoding=utf-8
sharding.jdbc.datasource.ds1.username=root
sharding.jdbc.datasource.ds1.password=root

# 水平拆分的数据库（表） 配置分库 + 分表策略 行表达式分片策略
# 分库策略
sharding.jdbc.config.sharding.default-database-strategy.inline.sharding-column=id
sharding.jdbc.config.sharding.default-database-strategy.inline.algorithm-expression=ds$->{id % 2}

# 分表策略 其中user为逻辑表 分表主要取决于age行
sharding.jdbc.config.sharding.tables.user.actual-data-nodes=ds$->{0..1}.user_$->{0..1}
sharding.jdbc.config.sharding.tables.user.table-strategy.inline.sharding-column=age
# 分片算法表达式
sharding.jdbc.config.sharding.tables.user.table-strategy.inline.algorithm-expression=user_$->{age % 2}

# 主键 UUID 18位数 如果是分布式还要进行一个设置 防止主键重复
#sharding.jdbc.config.sharding.tables.user.key-generator-column-name=id

# 打印执行的数据库以及语句
sharding.jdbc.config.props..sql.show=true
spring.main.allow-bean-definition-overriding=true
```
