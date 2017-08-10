#Springboot security
#### spring security是什么?
Spring Security是一个能够为基于Spring的企业应用系统提供声明式的安全访问控制解决方案的安全框架。它提供了一组可以在Spring应用上下文中配置的Bean，充分利用了Spring IoC，DI（控制反转Inversion of Control ,DI:Dependency Injection 依赖注入）和AOP（面向切面编程）功能，为应用系统提供声明式的安全访问控制功能，减少了为企业系统安全控制编写大量重复代码的工作。
 
spring security所需jar包
　　　　　
```maven
<dependency>
<groupId>org.springframework.security</groupId>
<artifactId>spring-security-web</artifactId>
<version>4.1.2.RELEASE</version>
</dependency>
<dependency>
<groupId>org.springframework.security</groupId>
<artifactId>spring-security-config</artifactId>
<version>4.1.2.RELEASE</version>
</dependency>
<dependency>
<groupId>org.springframework.security</groupId>
<artifactId>spring-security-taglibs</artifactId>
<version>4.1.2.RELEASE</version>
</dependency>
```
 

spring security在web.xml中的配置
 
```xml
<!-- Spring Secutiry4.1的过滤器链配置 -->
<filter>
<filter-name>springSecurityFilterChain</filter-name>
<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
<filter-mapping>
<filter-name>springSecurityFilterChain</filter-name>
<url-pattern>/*</url-pattern>
</filter-mapping>
```
 

spring security的配置文件内容如下
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/security"
xmlns:beans="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/security
http://www.springframework.org/schema/security/spring-security.xsd">

<debug />
<http security="none" pattern="/login.jsp" />
<http security="none" pattern="/static/**" />
<http use-expressions="true" auto-config="true">
<intercept-url pattern="/**" access="hasRole('ROLE_USER')" />

<!-- 同一时间内允许同一账号保持4个在线,error-if-maximum-exceeded="true"表示第第四个以后的登不进去 -->
<session-management>
<concurrency-control max-sessions="4"
error-if-maximum-exceeded="true" />
</session-management>
<csrf disabled="true"/>
<form-login login-page="/login.jsp"
authentication-failure-handler-ref="authenticationFailureHandlerImpl"
authentication-success-handler-ref="authenticationSuccessHandlerImpl" />
<logout logout-success-url="/logout.jsp" logout-url="logout"
invalidate-session="true" delete-cookies="JSESSIONID" />
</http>

<authentication-manager>
<!-- <authentication-provider> -->
<!-- <user-service> -->
<!-- <user name="admin" password="123" authorities="ROLE_USER"/> -->
<!-- </user-service> -->
<!-- </authentication-provider> -->
<authentication-provider user-service-ref="userService">
<password-encoder hash="bcrypt" />
</authentication-provider>
</authentication-manager>

<beans:bean id="userService" class="com.**.user.service.impl.UserServiceImpl" />

<!-- 认证成功调用  主要实现AuthenticationSuccessHandler这个类的onAuthenticationSuccess方法-->
<beans:bean id="authenticationSuccessHandlerImpl"
class="com.**.utils.springsecurity.AuthenticationSuccessHandlerImpl">
<beans:property name="url" value="/welcome.jsp" />
</beans:bean>

<!-- 认证失败调用 主要实现AuthenticationFailureHandler类的onAuthenticationFailure-- >
<beans:bean id="authenticationFailureHandlerImpl"
class="com.**.utils.springsecurity.AuthenticationFailureHandlerImpl">
<beans:property name="errorUrl" value="/error.jsp" />
</beans:bean>

</beans:beans>
```
 
```java
com.**.user.service.impl.UserServiceImp.java

public class UserServiceImpl implements UserDetailsService{

　　@Autowired
　　private UserDao userDao;

　　public UserDetails loadUserByUsername(String username) {
　　　　UserDetails details = null;
　　　　try {
　　　　　　// 用户名,密码,是否激活,accountnonexpired如果帐户没有过期设置为true
　　　　　　// credentialsnonexpired如果证书没有过期设置为true
　　　　　　// accountnonlocked如果帐户不锁定设置为true
　　　　　　com.aoyu.user.entity.User u = this.getUser(username);

　　　　　　//目前是把角色给写死了
　　　　　　details = new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), 　　　　　　　　　　　　u.isEnabled(),u.isAccountNonExpired(),u.isCredentialsNonExpired(),u.isAccountNonLocked(),AuthorityUtils.createAuthorityList("ROLE_USER"));

　　　　} catch (UsernameNotFoundException usernameNotFoundException) {
　　　　　　usernameNotFoundException.printStackTrace();
　　　　} catch (Exception e) {
　　　　　　e.printStackTrace();
　　　　}
　　　　return details;

　　}
}
```
