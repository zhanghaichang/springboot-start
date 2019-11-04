# 阿里巴巴druid数据源


* 可以监控数据库访问性能，Druid内置提供了一个功能强大的StatFilter插件，能够详细统计SQL的执行性能，这对于线上分析数据库访问性能有帮助。
替换DBCP和C3P0。Druid提供了一个高效、功能强大、可扩展性好的数据库连接池。
* 数据库密码加密。直接把数据库密码写在配置文件中，这是不好的行为，容易导致安全问题。DruidDruiver和DruidDataSource都支持PasswordCallback。
* SQL执行日志，Druid提供了不同的LogFilter，能够支持Common-Logging、Log4j和JdkLog，你可以按需要选择相应的LogFilter，监控你应用的数据库访问情况。
* 扩展JDBC，如果你要对JDBC层有编程的需求，可以通过Druid提供的Filter-Chain机制，很方便编写JDBC层的扩展插件。


## pom 依赖

```xml
<!-- 属性 -->
    <properties>
    <druid.version>1.0.18</druid.version>
    </properties>
<dependency>
     <groupId>com.alibaba</groupId>
     <artifactId>druid</artifactId>
     <version>${druid.version}</version>
 </dependency>
```
## 数据源配置

```
# 数据库访问配置
# 主数据源
spring.datasource.type=com.maobc.druid.MyDruidDataSource
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/maobctest
spring.datasource.username=root
spring.datasource.password=123456
 
# 初始化大小，最小，最大
spring.datasource.initialSize=1
spring.datasource.minIdle=5
spring.datasource.maxActive=20
 
spring.druid.initialSize=5                                 #初始化连接大小
spring.druid.minIdle=5                                     #最小连接池数量
spring.druid.maxActive=20                                  #最大连接池数量
spring.druid.maxWait=60000                                 #获取连接时最大等待时间，单位毫秒
#配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
spring.druid.timeBetweenEvictionRunsMillis=60000           
#配置一个连接在池中最小生存的时间，单位是毫秒
spring.druid.minEvictableIdleTimeMillis=300000           
#测试连接
spring.druid.validationQuery=select 'x'                   
#申请连接的时候检测，建议配置为true，不影响性能，并且保证安全性
spring.druid.testWhileIdle=true                            
#获取连接时执行检测，建议关闭，影响性能
spring.druid.testOnBorrow=false                            
#归还连接时执行检测，建议关闭，影响性能
spring.druid.testOnReturn=false 
#是否开启PSCache，PSCache对支持游标的数据库性能提升巨大，oracle建议开启，mysql下建议关闭                          
spring.druid.poolPreparedStatements=true  
#开poolPreparedStatements后生效                
spring.druid.maxPoolPreparedStatementPerConnectionSize=20  
#配置扩展插件，常用的插件有=>stat:监控统计  logback:日志  wall:防御sql注入
spring.druid.filters=stat,wall,logback
#通过connectProperties属性来打开mergeSql功能;慢SQL记录                  
spring.datasource.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000 
```

## DruidConfiguration配置

```java
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.xx.core.untils.AesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;import java.sql.SQLException;
 
@Configuration
public class DruidConfiguration {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.filters}")
    private String filters;
    @Value("{spring.datasource.connectionProperties}")
    private String connectionProperties;
 
    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource(){
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(AesUtils.decrypt(username));
        datasource.setPassword(AesUtils.decrypt(password));
        datasource.setDriverClassName(driverClassName);
 
        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);  
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);     datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            System.err.println("druid configuration initialization filter: "+ e);
        }
        datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }
 
    @Bean
    public ServletRegistrationBean statViewServle(){
       //根据配置中的url-pattern来访问内置监控页面，如果是上面的配置/druid/*，
         //内置监控页面的首页是/druid/index.html
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
       //配置allow和deny。deny优先于allow，如果在deny列表中，就算在allow列表中，
       //也会被拒绝。如果allow没有配置或者为空，则允许所有访问
        // IP白名单
        servletRegistrationBean.addInitParameter("allow","192.168.1.102,127.0.0.1");
        // IP黑名单(共同存在时，deny优先于allow)
        servletRegistrationBean.addInitParameter("deny","192.168.1.105");
        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername","druid");
        servletRegistrationBean.addInitParameter("loginPassword","druid");
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }
 
    @Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的格式
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
```

AesUtils工具类：

```java
//对称性加密算法
public class AesUtils {
    private static final String IV = "45C2E2ADA09216Buss";
    private static final String KEY = "PBBK0jSxCHAruRss5Sz7gUrQ==";
 
   //加密
    public static String encrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec key = new SecretKeySpec(Base64.getDecoder().decode("JAAK0jSxCHAruR5Sz7gUrQ=="), "AES");
            IvParameterSpec iv = new IvParameterSpec("40C2E2ADA09216Bu".getBytes("UTF-8"));
            cipher.init(1, key, iv);
            byte[] result = cipher.doFinal(content.getBytes());
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception var5) {
            throw new RuntimeException("AES encrypt error", var5);
        }
    }
   //解密
    public static String decrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("40C2E2ADA09216Bu".getBytes("UTF-8"));
            SecretKeySpec key = new SecretKeySpec(Base64.getDecoder().decode("JAAK0jSxCHAruR5Sz7gUrQ=="), "AES");
            cipher.init(2, key, iv);
            byte[] result = Base64.getDecoder().decode(content);
            return new String(cipher.doFinal(result));
        } catch (Exception var5) {
            throw new RuntimeException("AES decrypt error", var5);
        }
    }
 
    private AesUtils() {
    }
}
```

MyDruidDataSource :

```java

public class MyDruidDataSource extends DruidDataSource {
    private static final Logger logger = LoggerFactory.getLogger(MyDruidDataSource .class);
 
    public MyDruidDataSource () {
    }
 
    public void setUsername(String username) {
        logger.debug("Set username: {}", username);
        super.setUsername(AesUtils.decrypt(username));
    }
 
    public void setPassword(String password) {
        logger.debug("Set password: {}", password);
        super.setPassword(AesUtils.decrypt(password));
    }
}

```
