# Springboot + Mybatis + Redis
- 如何使用Spring-Boot快速搭建一个Web应用，并且采用Mybatis作为我们的ORM框架。为了提升性能，我们将Redis作为Mybatis的二级缓存。为了测试我们的代码，我们编写了单元测试，并且用Mysql数据库来生成我们的测试数据。通过该项目，我们希望读者可以快速掌握现代化Java Web开发的技巧以及最佳实践。
***
- 代码中一些关键点：
    * 自己实现的二级缓存，必须要有一个带id的构造函数，否则会报错。
我们使用Spring封装的redisTemplate来操作Redis。网上所有介绍redis做二级缓存的文章都是直接用jedis库，但是笔者认为这样不够Spring Style，而且，redisTemplate封装了底层的实现，未来如果我们不用jedis了，我们可以直接更换底层的库，而不用修改上层的代码。更方便的是，使用redisTemplate，我们不用关心redis连接的释放问题，否则新手很容易忘记释放连接而导致应用卡死。
需要注意的是，这里不能通过autowire的方式引用redisTemplate，因为RedisCache并不是Spring容器里的bean。所以我们需要手动地去调用容器的getBean方法来拿到这个bean，具体的实现方式请参考Github中的代码。
我们采用的redis序列化方式是默认的jdk序列化。所以数据库的查询对象（比如Product类）需要实现Serializable接口。
这样，我们就实现了一个优雅的、科学的并且具有Spring Style的Redis缓存类。
***
###### 环境

- 开发环境：Windows 10
- ide：Eclipse Nemo
- jdk：1.8
- Spring-Boot：1.5.6.RELEASE
- Redis：3.2.9
- Mysql：5.7