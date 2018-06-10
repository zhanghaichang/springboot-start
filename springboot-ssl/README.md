# HTTPS支持

SSL(Secure Sockets Layer，安全套接层)，这是一种为网络通信提供安全及数据完整性的一种安全协议，SSL在网络传输层对网络连接进行加密。SSL协议可以分为两层：SSL记录协议（SSL Record Protocol），它建立在可靠的传输协议如TCP之上，为高层协议提供数据封装、压缩、加密等基本功能支持；SSL握手协议（SSL Handshake Protocol），它建立在SSL记录协议之上，用于在实际数据传输开始之前，通信双方进行身份认证、协商加密算法、交换加密密钥等。在Web开发中，我们是通过HTTPS来实现SSL的。HTTPS是以安全为目标的HTTP通道，简单来说就是HTTP的安全版，即在HTTP下加入SSL层，所以说HTTPS的安全基础是SSL，不过这里有一个地方需要小伙伴们注意，就是我们现在市场上使用的都是TLS协议(Transport Layer Security，它来源于SSL)，而不是SSL，只不过由于SSL出现较早并且被各大浏览器支持因此成为了HTTPS的代名词，。你可以把HTTPS和SSL的关系理解成iPhone和富土康的关系，大概就是这样哈。 

ssl 生成方式配置方式见下面:

> keytool -genkey -alias tomcat  -storetype PKCS12 -keyalg RSA -keysize 2048  -keystore keystore.p12 -validity 3650


在application.properties中添加如下代码：
```
server.ssl.key-store=keystore.p12
server.ssl.key-store-password=111111
server.ssl.keyStoreType=PKCS12
server.ssl.keyAlias:tomcat
```
HTTP自动转向HTTPS

```java
  @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(8080);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(8443);
        return connector;
    }
```
这个时候当我们访问http://localhost:8080的时候系统会自动重定向到https://localhost:8443

