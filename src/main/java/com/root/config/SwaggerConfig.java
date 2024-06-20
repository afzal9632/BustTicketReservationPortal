package com.root.config;//package com.javainuse.bootmysqlcrud.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("JavaInUse Authentication Service"))
                .addSecurityItem(new SecurityRequirement().addList("JavaInUseSecurityScheme"))
                .components(new Components().addSecuritySchemes("JavaInUseSecurityScheme", new SecurityScheme()
                        .name("JavaInUseSecurityScheme").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));

    }
}







//package com.root.config;
//
//import io.swagger.v3.oas.models.info.Contact;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.context.SecurityContext;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//@Configuration
//public class SwaggerConfig {
//
//    public static  final String AUTHORIZATION_HEADER="Authorization";
//
//    private  ApiKey apiKeys(){
//        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
//    }
//
//    private List<SecurityContext> securityContexts(){
//
//        return Arrays.asList(SecurityContext.builder().securityRefrences(sf()).build());
//    }
//
//    private  List<SecutityRefrences> sf(){
//
//        AuthorizationScpe scope = new AuthorizationScope("global","accissEverything")
//        return Arrays.asList(new SecurityRefence("JWT ",new AuthozationScope[]{scope}));
//
//    }
//
//    @Bean
//    public Docket api(){
//
//
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(getInfo())
//                .securityContexts(securityContexts())
//                .securitySchemes(Arrays.asList((apiKeys())))
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any()).bulild();
//    }
//
//    private  ApiInfo getInfo(){
//
//        return  new ApiInfo("Blogging app",
//                "this project")
//                new Contact("Durgesh", Collections.emptyList())
//    }
//}
