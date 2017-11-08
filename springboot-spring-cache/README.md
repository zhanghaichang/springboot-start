# spring-boot-starter-cache redis 声明式缓存
* Spring 定义 CacheManager 和 Cache 接口用来统一不同的缓存技术。例如 JCache、 EhCache、 Hazelcast、 Guava、 Redis 等。在使用 Spring 集成 Cache 的时候，我们需要注册实现的 CacheManager 的 Bean。

* Spring Boot 为我们自动配置了 JcacheCacheConfiguration、 EhCacheCacheConfiguration、HazelcastCacheConfiguration、GuavaCacheConfiguration、RedisCacheConfiguration、SimpleCacheConfiguration 等

* 默认使用 ConcurrenMapCacheManager 在我们不使用其他第三方缓存依赖的时候，springboot自动采用ConcurrenMapCacheManager作为缓存管理器。

* 开启缓存技 在程序的入口中加入@ EnableCaching开启缓存技术

* 在需要缓存的方法上加入@Cacheable注解，这个方法就开启了缓存策略，当缓存有这个数据的时候，会直接返回数据，不会等待去查询数据库。

```xml
@Cacheable将查询结果缓存到redis中，（key="#p0"）指定传入的第一个参数作为redis的key。

@CachePut，指定key，将更新的结果同步到redis中

@CacheEvict，指定key，删除缓存数据，allEntries=true,方法调用后将立即清除缓存
```
### 项目依赖

```maven
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```

