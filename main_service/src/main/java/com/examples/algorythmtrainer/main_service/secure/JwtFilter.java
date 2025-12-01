package com.examples.algorythmtrainer.main_service.secure;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends GenericFilterBean {

    private static final String AUTHORIZATION = "Authorization";

    private final JwtService jwtService;

    @Autowired
    public JwtFilter(final JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
            throws IOException, ServletException {

        String token = null;
        if (request instanceof HttpServletRequest httpRequest) {
            token = getTokenFromRequest(httpRequest);
        }

        if (token != null && jwtService.validateAccessToken(token)) {
            log.debug("Access token validated");
            final Claims claims = jwtService.getAccessClaims(token);
            final JwtAuthentication jwtInfoToken = JwtUtils.generate(claims);
            jwtInfoToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtInfoToken);
        }
        fc.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}