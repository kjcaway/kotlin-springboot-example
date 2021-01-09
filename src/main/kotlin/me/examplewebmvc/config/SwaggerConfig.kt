package me.examplewebmvc.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun docket(): Docket{
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(this.apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("me.examplewebmvc.api"))
            .paths(PathSelectors.ant("/**"))
            .build()
    }

    private fun apiInfo(): ApiInfo{
        return ApiInfoBuilder()
            .title("Demo Application")
            .description("Demo Application Api")
            .build()
    }
}