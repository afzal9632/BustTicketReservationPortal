
package com.root.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAccessDeniedExceptionHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        log.info("Forbidden: You don't have permission to access this resource.");
        response.setStatus(403);
        response.getWriter().write("Forbidden: You don't have permission to access this resource.");
        response.addHeader("reason","Forbidden: You don't have permission to access this resource.");
    }
}
