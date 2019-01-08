# WebService

最近几年restful大行其道，大有取代传统soap web service的趋势， 但是一些特有或相对老旧的系统依然使用了传统的soap web service，例如银行、航空公司的机票查询接口等

```maven
   <!-- CXF webservice -->
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-spring-boot-starter-jaxws</artifactId>
        <version>3.2.4</version>
    </dependency>
    <!-- CXF webservice -->
```

[访问地址](http://localhost:8001/webservices/CommonService?wsdl)