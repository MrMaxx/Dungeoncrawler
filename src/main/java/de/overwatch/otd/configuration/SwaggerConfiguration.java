package de.overwatch.otd.configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableSwagger
public class SwaggerConfiguration {

        @Autowired
        private SpringSwaggerConfig springSwaggerConfig;

        @Bean
        public SwaggerSpringMvcPlugin customImplementation(){
            return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                    .includePatterns(".*api/v1.*");
        }


}
