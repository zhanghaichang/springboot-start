# 共享Session-spring-session-data-redis
###### redis介绍
Redis是目前业界使用最广泛的内存数据存储。相比memcached，redis支持更丰富的数据结构，例如hashes, lists, sets等，同时支持数据持久化。除此之外，Redis还提供一些类数据库的特性，比如事务，HA，主从库。可以说Redis兼具了缓存系统和数据库的一些特性，因此有着丰富的应用场景。  
* 引入 spring-boot-starter-redis  

```maven
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-redis</artifactId>  
</dependency>  
```

```yml
# REDIS (RedisProperties)
# Redis数据库索引（默认为0）
spring.redis.database=0  
# Redis服务器地址
spring.redis.host=192.168.0.58
# Redis服务器连接端口
spring.redis.port=6379  
# Redis服务器连接密码（默认为空）
spring.redis.password=
# 连接池最大连接数（使用负值表示没有限制）
spring.redis.pool.max-active=8  
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.pool.max-wait=-1  
# 连接池中的最大空闲连接
spring.redis.pool.max-idle=8  
# 连接池中的最小空闲连接
spring.redis.pool.min-idle=0  
# 连接超时时间（毫秒）
spring.redis.timeout=0
```
***
##### 缓存名词的介绍
* 缓存命中率
    * 即从缓存中读取数据的次数 与 总读取次数的比率，命中率越高越好： 
    * 命中率 = 从缓存中读取次数 / (总读取次数[从缓存中读取次数 + 从慢速设备上读取的次数]) 
    * Miss率 = 没有从缓存中读取的次数 / (总读取次数[从缓存中读取次数 + 从慢速设备上读取的次数])这是一个非常重要的监控指标，如果做缓存一定要健康这个指标来看缓存是否工作良好；

* 缓存策略
    * Eviction policy 移除策略，即如果缓存满了，从缓存中移除数据的策略；常见的有LFU、LRU、FIFO： 
    * FIFO（First In First Out）：先进先出算法，即先放入缓存的先被移除； 
    * LRU（Least Recently Used）：最久未使用算法，使用时间距离现在最久的那个被移除； 
    * LFU（Least Frequently Used）：最近最少使用算法，一定时间段内使用次数（频率）最少的那个被移除；
    * TTL（Time To Live ）存活期，即从缓存中创建时间点开始直到它到期的一个时间段（不管在这个时间段内有没有访问都将过期）
    * TTI（Time To Idle）空闲期，即一个数据多久没被访问将从缓存中移除的时间。
###### 全局缓存配置
* [注释介绍](http://blog.csdn.net/whatlookingfor/article/details/51833378)
    * @Cacheable 的作用 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
    * @CachePut 的作用 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用
    * @CachEvict 的作用 主要针对方法配置，能够根据一定的条件对缓存进行清空
    * @Caching 有时候我们可能组合多个Cache注解使用；比如用户新增成功后，我们要添加id–>user；username—>user；email—>user的缓存；此时就需要@Caching组合多个注解标签了。
    * @CacheConfig 一次性声明  
```java
@Cacheable(value="accountCache")// 使用了一个缓存名叫 accountCache 
public Account getAccountByName(String userName) {
     // 方法内部实现不考虑缓存逻辑，直接实现业务
     System.out.println("real query account."+userName); 
     return getFromDB(userName); 
}
@CachePut(value="accountCache",key="#account.getName()")// 更新accountCache 缓存
public Account updateAccount(Account account) { 
   return updateDB(account); 
} 
@CacheEvict(value="accountCache",key="#account.getName()")// 清空accountCache 缓存  
public void updateAccount(Account account) {
     updateDB(account); 
} 

@CacheEvict(value="accountCache",allEntries=true)// 清空accountCache 缓存
public void reload() {
     reloadAll()
}

@Cacheable(value="accountCache",condition="#userName.length() <=4")// 缓存名叫 accountCache 
public Account getAccountByName(String userName) { 
 // 方法内部实现不考虑缓存逻辑，直接实现业务
 return getFromDB(userName); 
}
@CacheConfig("books")
public class BookRepositoryImpl implements BookRepository {

    @Cacheable
    public Book findBook(ISBN isbn) {...}
}
//@Cacheable将在执行方法之前( #result还拿不到返回值)判断condition，如果返回true，则查缓存； 
@Cacheable(value = "user", key = "#id", condition = "#id lt 10")
public User conditionFindById(final Long id)  

//@CachePut将在执行完方法后（#result就能拿到返回值了）判断condition，如果返回true，则放入缓存； 
@CachePut(value = "user", key = "#id", condition = "#result.username ne 'zhang'")  
public User conditionSave(final User user)   

//@CachePut将在执行完方法后（#result就能拿到返回值了）判断unless，如果返回false，则放入缓存；（即跟condition相反）
@CachePut(value = "user", key = "#user.id", unless = "#result.username eq 'zhang'")
public User conditionSave2(final User user)   

//@CacheEvict， beforeInvocation=false表示在方法执行之后调用（#result能拿到返回值了）；且判断condition，如果返回true，则移除缓存；
@CacheEvict(value = "user", key = "#user.id", beforeInvocation = false, condition = "#result.username ne 'zhang'")  
public User conditionDelete(final User user)   
```