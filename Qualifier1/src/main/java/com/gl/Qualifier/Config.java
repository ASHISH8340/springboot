package com.gl.Qualifier;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ComponentScan(
        basePackages = {"dog"}
)
public class Config {
    public Config() {
    }

    @Bean({"student1"})
    public Student getStudent() {
        System.out.println("get student object");
        return new Student("student1");
    }

    @Bean({"student2"})
    @Lazy
    public Student createStudent() {
        System.out.println("create student object");
        return new Student("student2");
    }
}
