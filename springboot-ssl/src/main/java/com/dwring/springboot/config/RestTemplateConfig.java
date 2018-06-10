package com.dwring.springboot.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
/**
 * 传送相关设置
 * @author XuHan
 *
 */
@Configuration
public class RestTemplateConfig 
{
	
    /***
     * 自定义设置http请求链接时间
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "http.api.connection")
    public HttpComponentsClientHttpRequestFactory customHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }

   	
	@Bean(name="restTemplate")
	public RestTemplate getClient()
	{
		// 添加内容转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());  

        RestTemplate restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(buildHttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
		return restTemplate;
	}
	
	@Value("${config.httpclient.connect.timeout}")
	private int connTimeout;
	@Value("${config.httpclient.socket.timeout}")
	private int socketTimeout;
	@Bean(name="clientHttpRequestFactory")
	public HttpComponentsClientHttpRequestFactory buildHttpComponentsClientHttpRequestFactory()
	{
		HttpClient httpClient = createHttpClientBuilder().build();

        // httpClient连接配置，底层是配置RequestConfig
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        // 连接超时
        clientHttpRequestFactory.setConnectTimeout(connTimeout);
        // 数据读取超时时间，即SocketTimeout
        clientHttpRequestFactory.setReadTimeout(socketTimeout);
        // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        clientHttpRequestFactory.setConnectionRequestTimeout(200);
        
        return clientHttpRequestFactory;
	}
	
	@Bean(name="httpClientBuilder")
	public HttpClientBuilder createHttpClientBuilder()
	{
		HttpClientBuilder httpClientBuilder =HttpClientBuilder.create();
		httpClientBuilder.setConnectionManager(createPoolingHttpClientConnManager());
		
		// 重试次数，默认是3次，没有开启
		httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2, true));
		// 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
        
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));

        httpClientBuilder.setDefaultHeaders(headers);
		return httpClientBuilder;
	}
	
	/**
	 * @return
	 */
	@Value("${config.httpclient.maxTotal}")
	private int maxTotal;
	@Value("${config.httpclient.defaultMaxPerRoute}")
	private int defaultMaxPerRoute;
	@Bean(name="pollingConnectionManager")
	public PoolingHttpClientConnectionManager createPoolingHttpClientConnManager()
	{
		// 长连接保持30秒
		PoolingHttpClientConnectionManager pollingConnectionManager =new  PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
		// 总连接数
		pollingConnectionManager.setMaxTotal(maxTotal);
		// 同路由的并发数
		pollingConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
		return pollingConnectionManager;
	}
}
