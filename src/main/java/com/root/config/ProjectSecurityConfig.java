package com.root.config;

import com.root.filter.JWTTokenValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.HandlerExceptionResolver;


import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;


    @Bean
    public JWTTokenValidatorFilter jwtTokenValidatorFilter(){
        return new JWTTokenValidatorFilter(exceptionResolver);
    }


    @Autowired
    private CustomAccessDeniedExceptionHandler customAccessDeniedExceptionHandler;


    private static final String[] WHITE_LIST_URL = { "/api/v1/auth/**", "/v2/api-docs", "/v3/api-docs",
            "/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
            "/configuration/security", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html", "/api/auth/**",
            "/api/test/**" ,"/users/register", "/logout", "/authenticate"};

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {


        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(Arrays.asList("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
                })).csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/admin/**","/users/admin/**", "user/addAuthorityToUser/**", "/bus/add", "/bus/update", "/bus/delete/**", "/route/admin/**", "/route/byId/**").hasRole("ADMIN")
                        .requestMatchers("/authors/get/**", "/current-user","/users/update", "/routes", "/buses").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/user/**","/posttest/**", "/bus/get/**", "/bus/type**","/reservation/user/**").hasRole("USER")
                        .requestMatchers("/logout", "/user").authenticated()
                        .requestMatchers(WHITE_LIST_URL).permitAll())
                .exceptionHandling(e->e.accessDeniedHandler(customAccessDeniedExceptionHandler))
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
              ;

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

