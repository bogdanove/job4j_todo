package ru.job4j.todo.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class AuthFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var uri = request.getRequestURI();
        var userLoggedIn = request.getSession().getAttribute("user") != null;
        if (uri.startsWith("/tasks") && !userLoggedIn) {
            var loginPageUrl = request.getContextPath() + "/users/loginPage";
            response.sendRedirect(loginPageUrl);
            return;
        }
        chain.doFilter(request, response);
    }
}
