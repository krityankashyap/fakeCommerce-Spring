package org.example.fakecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // It enables jpa auditing
public class FakeCommerceApplication {

//    Dotenv dotenv = Dotenv.load();
//        System.setProperty("PORT", dotenv.get("PORT"));

    public static void main(String[] args) {
        SpringApplication.run(FakeCommerceApplication.class, args);
    }

}
