package br.ucsal.armentanoc.appeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AppEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppEurekaServerApplication.class, args);
	}

}
