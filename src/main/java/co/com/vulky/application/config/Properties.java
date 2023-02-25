package co.com.vulky.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:message.properties", ignoreResourceNotFound = true)
public class Properties {
}
