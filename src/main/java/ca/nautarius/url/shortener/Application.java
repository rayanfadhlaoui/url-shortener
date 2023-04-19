package ca.nautarius.url.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ca.nautarius.url.shortener")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}