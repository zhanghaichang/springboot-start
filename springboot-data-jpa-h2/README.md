# Jpa-h2 多数据源
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
#### JpaRepository相关查询功能
* a.Spring Data JPA框架在进行方法名解析时，会先把方法名多余的前缀截取掉，比如find、findBy、read、readBy、get、getBy，然后对剩下部分进行解析。
* b.假如创建如下的查询：findByUserDepUuid()，框架在解析该方法时，首先剔除
findBy，然后对剩下的属性进行解析，假设查询实体为Doc。
    * 1：先判断userDepUuid （根据POJO 规范，首字母变为小写）是否为查询实体的一个
属性，如果是，则表示根据该属性进行查询；如果没有该属性，继续第二步；
    * 2：从右往左截取第一个大写字母开头的字符串此处为Uuid），然后检查剩下的字符串是
否为查询实体的一个属性，如果是，则表示根据该属性进行查询；如果没有该属性，
则重复第二步，继续从右往左截取；最后假设user为查询实体的一个属性；
    * 3：接着处理剩下部分（DepUuid），先判断user 所对应的类型是否有depUuid属性，如
果有，则表示该方法最终是根据“ Doc.user.depUuid” 的取值进行查询；否则继
续按照步骤2 的规则从右往左截取，最终表示根据“Doc.user.dep.uuid” 的值进
行查询。
    * 4:可能会存在一种特殊情况，比如Doc包含一个user 的属性，也有一个userDep 属
性，此时会存在混淆。可以明确在属性之间加上"_" 以显式表达意图，比如
"findByUser_DepUuid()" 或者"findByUserDep_uuid()"
* c.特殊的参数： 还可以直接在方法的参数上加入分页或排序的参数，比如：
Page<UserModel> findByName(String name, Pageable pageable);
List<UserModel> findByName(String name, Sort sort);
* d.也可以使用JPA的NamedQueries，方法如下：
    * 1：在实体类上使用@NamedQuery，示例如下：
@NamedQuery(name = "UserModel.findByAge",query = "select o from UserModel
o where o.age >= ?1")
    * 2：在自己实现的DAO的Repository接口里面定义一个同名的方法，示例如下：
public List<UserModel> findByAge(int age);
    * 3：然后就可以使用了，Spring会先找是否有同名的NamedQuery，如果有，那么就不
会按照接口定义的方法来解析。
* e.还可以使用@Query来指定本地查询，只要设置nativeQuery为true，比如：
@Query(value="select * from tbl_user where name like %?1" ,nativeQuery=true)
public List<UserModel> findByUuidOrAge(String name);
注意：当前版本的本地查询不支持翻页和动态的排序
* f.使用命名化参数，使用@Param即可，比如：
@Query(value="select o from UserModel o where o.name like %:nn")
public List<UserModel> findByUuidOrAge(@Param("nn") String name);
* g.同样支持更新类的Query语句，添加@Modifying即可，比如：
@Modifying
@Query(value="update UserModel o set o.name=:newName where o.name like %:nn")
public int findByUuidOrAge(@Param("nn") String name,@Param("newName") String
newName);
注意：
    * 1：方法的返回值应该是int，表示更新语句所影响的行数
    * 2：在调用的地方必须加事务，没有事务不能正常执行
* f.创建查询的顺序
Spring Data JPA 在为接口创建代理对象时，如果发现同时存在多种上述
情况可用，它该优先采用哪种策略呢？
<jpa:repositories> 提供了query-lookup-strategy 属性，用以指定查
找的顺序。它有如下三个取值：
    * 1：create-if-not-found：如果方法通过@Query指定了查询语句，则使用该语句实现
查询；如果没有，则查找是否定义了符合条件的命名查询，如果找到，则使用该
命名查询；如果两者都没有找到，则通过解析方法名字来创建查询。这是querylookup-
strategy 属性的默认值
    * 2：create：通过解析方法名字来创建查询。即使有符合的命名查询，或者方法通过
@Query指定的查询语句，都将会被忽略
    * 3：use-declared-query：如果方法通过@Query指定了查询语句，则使用该语句实现
查询；如果没有，则查找是否定义了符合条件的命名查询，如果找到，则使用该
命名查询；如果两者都没有找到，则抛出异常
```xml
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-jpa</artifactId>
    <version>2.0.0.RC2</version>
</dependency>
```

