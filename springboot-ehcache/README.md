# Spring Boot ehcache

>EhCache 是一个纯Java的进程内缓存框架，具有快速、精干等特点，是Hibernate中默认的CacheProvider。Ehcache提供了多种缓存策略，主要分为内存和磁盘两级，所以无需担心容量问题。

### cache四个注解

```
@Cacheable  在方法执行前Spring先是否有缓存数据，如果有直接返回。如果没有数据，调用方法并将方法返回值存放在缓存当中。这个注解会先查询是否有缓存过的数据，如果有，就直接返回原来缓存好的数据，如果没有，则再执行一次方法，将方法的返回结果放到缓存中。

@CachePut   无论怎样，都将方法的返回结果放到缓存当中。这个注解不会询问是否有缓存好的数据，而是每次都会执行方法，将方法的返回结果放到缓存中，相当于每次都更新缓存中的数据，每次缓存中的数据都是最新的一次缓存数据。

@CacheEvict   将一条或者多条数据从缓存中删除。这个是删除一条或者多条的缓存数据。

@Caching  可以通过@Caching注解组合多个注解集合在一个方法上

```

ehcache.xml 文件配置详解

```
diskStore：为缓存路径，ehcache分为内存和磁盘两级，此属性定义磁盘的缓存位置。
defaultCache：默认缓存策略，当ehcache找不到定义的缓存时，则使用这个缓存策略。只能定义一个。
name:缓存名称。
maxElementsInMemory:缓存最大数目
maxElementsOnDisk：硬盘最大缓存个数。
eternal:对象是否永久有效，一但设置了，timeout将不起作用。
overflowToDisk:是否保存到磁盘，当系统当机时
timeToIdleSeconds:设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。
timeToLiveSeconds:设置对象在失效前允许存活时间（单位：秒）。最大时间介于创建时间和失效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0.，也就是对象存活时间无穷大。
diskPersistent：是否缓存虚拟机重启期数据 Whether the disk store persists between restarts of the Virtual Machine. The default value is false.diskSpoolBufferSizeMB：这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。
diskExpiryThreadIntervalSeconds：磁盘失效线程运行时间间隔，默认是120秒。
memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时，Ehcache将会根据指定的策略去清理内存。默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
clearOnFlush：内存数量最大时是否清除。
memoryStoreEvictionPolicy:可选策略有：LRU（最近最少使用，默认策略）、FIFO（先进先出）、LFU（最少访问次数）。
FIFO，first in first out，先进先出。 
LFU， Less Frequently Used，一直以来最少被使用的。如上面所讲，缓存的元素有一个hit属性，hit值最小的将会被清出缓存。 
LRU，Least Recently Used，最近最少使用的，缓存的元素有一个时间戳，当缓存容量满了，而又需要腾出地方来缓存新的元素的时候，那么现有缓存元素中时间戳离当前时间最远的元素将被清出缓存。
```
***

##### H2 Database
H2 Database做为轻量级的内嵌数据库，功能十分强大，而且运行时只需要一个jar包即可
更详细的对比见官网页面： [官网](http://www.h2database.com/html/features.html#comparison)
maven中添加依赖项
 ```xml
<dependency>
   <groupId>com.h2database</groupId>
   <artifactId>h2</artifactId>
   <version>1.3.176</version>            
 </dependency> 
 ```
 配置以后，在浏览器输入 `http://localhost:8080/h2-console`  然后输入用户名和密码，选择好合适的语言，就可以进入数据库管理应用啦
