package co.com.vulky.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {VulkyApplication.MAIN_APP})
@EntityScan(basePackages = {VulkyApplication.MAIN_ENTITIES})
@SpringBootApplication()
public class VulkyApplication {

    public static final String MAIN_APP ="co.com.vulky";
    public static final String MAIN_ENTITIES ="co.com.vulky.infraestructure.data.entity";

    public static void main(String[] args) {
        SpringApplication.run(VulkyApplication.class, args);
    }

}
