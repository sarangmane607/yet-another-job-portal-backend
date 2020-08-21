package com.yajp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.yajp.security.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class YetAnotherJobPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(YetAnotherJobPortalApplication.class, args);
	}
}
