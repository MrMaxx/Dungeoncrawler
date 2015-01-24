package de.overwatch.otd;


import de.overwatch.otd.configuration.*;
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
        "de.overwatch.otd.domain",
        "de.overwatch.otd.repository",
        "de.overwatch.otd.game"
})
@Import({
        SchedulingConfiguration.class,
        SpringMvcConfiguration.class,
        SwaggerConfiguration.class,
        WebSecurityConfiguration.class,
        OAuth2ServerConfiguration.class
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
