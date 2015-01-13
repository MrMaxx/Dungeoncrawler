package de.overwatch.otd.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = {
        "de.overwatch.otd.job"})
public class SchedulingConfiguration {

}
