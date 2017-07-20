# Spring Boot+Shiro
* [Apache Shiro](http://shiro.apache.org) 是一个功能强大、灵活的，开源的安全框架。它可以干净利落地处理身份验证、授权、企业会话管理和加密。
Apache Shiro的首要目标是易于使用和理解。安全通常很复杂，甚至让人感到很痛苦，但是Shiro却不是这样子的。一个好的安全框架应该屏蔽复杂性，向外暴露简单、直观的API，来简化开发人员实现应用程序安全所花费的时间和精力。  
***
* Shiro能做什么呢？  
    * 验证用户身份
    * 用户访问权限控制，比如：1、判断用户是否分配了一定的安全角色。2、判断用户是否被授予完成某个操作的权限
    * 在非 web 或 EJB 容器的环境下可以任意使用Session API
    * 可以响应认证、访问控制，或者 Session 生命周期中发生的事件
    * 可将一个或以上用户安全数据源数据组合成一个复合的用户 "view"(视图)
    * 支持单点登录(SSO)功能
    * 支持提供“Remember Me”服务，获取用户关联信息而无需登录。
* RBAC 是基于角色的访问控制（Role-Based Access Control ）在 RBAC 中，权限与角色相关联，用户通过成为适当角色的成员而得到这些角色的权限。这就极大地简化了权限的管理。这样管理都是层级相互依赖的，权限赋予给角色，而把角色又赋予用户，这样的权限设计很清楚，管理起来很方便。
* [详细说明文档](http://www.cnblogs.com/ityouknow/p/7089177.html)
* [Druid Monitor](http://localhost:8088/druid/index.html) username: admin password: admin
