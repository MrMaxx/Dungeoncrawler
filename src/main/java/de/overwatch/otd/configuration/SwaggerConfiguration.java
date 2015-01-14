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
                    .apiInfo(apiInfo())
                    .includePatterns(".*api/v1.*");
        }

        // Todo: later
        private ApiInfo apiInfo() {
            ApiInfo apiInfo = new ApiInfo(
                    "Online Tower Defense",
                    "Online Tower Defense lts you play both sides of a regular Tower Defense game. Take the role of the attacker, of the defender.",
                    "",
                    "maximilian.hoeflich@googlemail.com",
                    "None Yet",
                    ""
            );
            return apiInfo;
        }
}
