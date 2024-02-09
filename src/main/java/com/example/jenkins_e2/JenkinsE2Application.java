package com.example.jenkins_e2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class JenkinsE2Application {

    public static void main(String[] args) {
        SpringApplication.run(JenkinsE2Application.class, args);
    }

}
