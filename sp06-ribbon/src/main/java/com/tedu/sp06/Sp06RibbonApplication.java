package com.tedu.sp06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class Sp06RibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp06RibbonApplication.class, args);
	}
	
	//创建 RestTemplate 实例，并存入 spring 容器
	@LoadBalanced	//生成一个动态代理对象
					//用动态代理,在RestTemplate上切入负载均衡代码
	@Bean
	public RestTemplate getRestTemplate() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(1000);
		factory.setReadTimeout(1000);
		return new RestTemplate(factory);
	}

}
