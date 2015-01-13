package de.overwatch.otd;


import de.overwatch.otd.configuration.SchedulingConfiguration;
import de.overwatch.otd.configuration.SpringMvcConfiguration;
import de.overwatch.otd.configuration.SwaggerConfiguration;
import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan(basePackages = {
        "de.overwatch.otd.controller",
        "de.overwatch.otd.service",
        "de.overwatch.otd.repository",
        "de.overwatch.otd.domain"
})
@Import({
        SpringMvcConfiguration.class,
        SchedulingConfiguration.class,
        SwaggerConfiguration.class
})
public class Application {

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
