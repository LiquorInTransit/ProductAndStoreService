package com.gazorpazorp.ProductAndStoreService;

import javax.annotation.PostConstruct;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.gazorpazorp.model.Product;
import com.gazorpazorp.service.ProductRepositoryCreationService;
import com.gazorpazorp.service.ProductRepositoryUpdateService;
import com.netflix.appinfo.AmazonInfo;

@SpringBootApplication(scanBasePackages="com.gazorpazorp")
@EnableJpaRepositories("com.gazorpazorp.repository")
@EntityScan(basePackages="com.gazorpazorp")
@EnableEurekaClient
@EnableFeignClients("com.gazorpazorp.client")
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProductAndStoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductAndStoreServiceApplication.class, args);
	}
	
	@Autowired
	ProductRepositoryCreationService PRCService;
	@Autowired
	ProductRepositoryUpdateService PRUService;
	
	@PostConstruct
	public void getProducts() {
		PRCService.start();
	}
	
	@Scheduled(cron = "0 0 2 * * *")
	public void updateRepo() {
		try {
			PRUService.wait(new Long(5000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PRUService.start();
	}
	
	@PostConstruct
	public void getDbManager(){
	   DatabaseManagerSwing.main(
		new String[] { "--url", "jdbc:hsqldb:mem:test://localhost/test?characterEncoding=UTF-8", "--user", "SA", "--password", ""});
	}
	
	
	@Configuration
	public static class RepositoryConfig extends RepositoryRestConfigurerAdapter {
	    @Override
		public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	        config.exposeIdsFor(Product.class);
	        config.setBasePath("api");
	    }
	}
	
	@Bean
	@Profile("!dev")
	public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils utils) 
	{
		EurekaInstanceConfigBean instance = new EurekaInstanceConfigBean(utils);
		AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
		instance.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
		instance.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4));
		instance.setDataCenterInfo(info);
		instance.setNonSecurePort(8080);
		return instance;
	}
	
//	@Bean
//	RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext context) {
//		if (context == null) return null;
//		return new CustomOAuth2FeignRequestInterceptor(context);
//	}
}
