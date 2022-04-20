package org.pickwicksoft.mountainfever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MountainFeverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MountainFeverApplication.class, args);
    }

}
