package org.example.fakecommerce;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // It enables jpa auditing
public class FakeCommerceApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        System.setProperty("New_Relic_License_Key", dotenv.get("New_Relic_License_Key"));

        SpringApplication.run(FakeCommerceApplication.class, args);
    }

}
