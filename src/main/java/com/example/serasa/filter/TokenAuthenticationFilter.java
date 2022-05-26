package com.example.serasa.filter;

import com.example.serasa.model.User;
import com.example.serasa.repository.UserRepository;
import com.example.serasa.service.impl.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserRepository repository;

    public TokenAuthenticationFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenFromHeader = getTokenFromHeader(request);
        boolean tokenValid = tokenService.isTokenValid(tokenFromHeader);

        if (tokenValid) {
            this.authenticate(tokenFromHeader);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String tokenFromHeader) {

        Long id = tokenService.getTokenId(tokenFromHeader);

        Optional<User> optionalUser = repository.findById(id);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    private String getTokenFromHeader(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }

}
