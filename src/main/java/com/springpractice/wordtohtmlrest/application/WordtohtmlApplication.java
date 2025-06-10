package com.springpractice.wordtohtmlrest.application;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import jakarta.annotation.PostConstruct;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WordtohtmlApplication implements ApplicationListener<ContextRefreshedEvent> {

	public static void main(String[] args) {
		SpringApplication.run(WordtohtmlApplication.class, args);
	}
	
	@Override
	public void onApplicationEvent (ContextRefreshedEvent event) {
		/*ConfigurableEnvironment env = (ConfigurableEnvironment) event.getApplicationContext().getEnvironment();
		MutablePropertySources propertySources = env.getPropertySources();
		StreamSupport.stream(propertySources.spliterator(), false)
			.filter(EnumerablePropertySource.class::isInstance)
			.map(ps -> ((EnumerablePropertySource<?>) ps).getPropertyNames()).flatMap(Arrays::<String>stream)
			.forEach(key -> log.info("{}={}", key, env.getProperty(key)));*/
	}
}
