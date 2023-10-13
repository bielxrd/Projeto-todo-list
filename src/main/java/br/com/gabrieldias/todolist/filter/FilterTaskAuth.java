package br.com.gabrieldias.todolist.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Pegar a autenticacao (usuario e senha)
        var authorization = request.getHeader("Authorization");


        var authEncoded = authorization.substring("Basic".length()).trim();

        byte [] authDecode = Base64.getDecoder().decode(authEncoded);

        var authString = new String(authDecode);
        String [] crendetials = authString.split(":");
        String username = crendetials[0];
        String password = crendetials[1];
        System.out.println(authString);
        filterChain.doFilter(request, response);
    }
}
