package com.example.pepino;

import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private ApplicationContext ctx;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logProfilesAndBeans(ctx, log, this.getClass().getName());
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@PostConstruct
	public void onStartup() {
		log.info("{} is starting", this.getClass().getName());
	}

	@PreDestroy
	public void onDestroy() {
		log.info("{} is shutting down", this.getClass().getName());
	}

	public void logProfilesAndBeans(ApplicationContext ctx, Logger log, String appName) {
		String profileNames = Arrays.toString(ctx.getEnvironment().getActiveProfiles());
		log.info("Starting {} with profile: {}", appName, profileNames);

		if (log.isDebugEnabled()) {
			log.trace("Let's spill the beans:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				log.trace(beanName);
			}

			log.trace("Beans have been spilled");
		}
	}

	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
				.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping
				.getHandlerMethods();
		map.forEach((key, value) -> log.info("{} {}", key, value));
	}
}