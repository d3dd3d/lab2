package com.d3d;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import com.d3d.repository.CalculatorRepository;
import com.d3d.repository.JdbcCaclculatorRepository;
@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})
@EnableJdbcRepositories(basePackageClasses = {
    JdbcCaclculatorRepository.class, CalculatorRepository.class
} )
public class Main {
    public static void main(String[] args) {
        SpringApplication
            .run(Main.class,args);
    }
}