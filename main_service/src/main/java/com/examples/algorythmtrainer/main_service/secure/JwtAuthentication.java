package com.examples.algorythmtrainer.main_service.secure;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentication implements Authentication {

    private boolean authenticated;
    private String login;
    private Integer id;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return java.util.Collections.singletonList(
                new org.springframework.security.core.authority.SimpleGrantedAuthority(role.toUpperCase())
        );
    }

    @Override
    public Object getCredentials() { return null; }

    @Override
    public Object getDetails() { return null; }

    @Override
    public Object getPrincipal() { return login; }

    @Override
    public boolean isAuthenticated() { return authenticated; }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() { return id.toString(); }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
