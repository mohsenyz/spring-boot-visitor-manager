/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

/**
 *
 * @author mphj
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        String url = ((HttpServletRequest) request).getRequestURI();
        HttpSession httpSession = ((HttpServletRequest) request).getSession();
        if (url.startsWith("/admin")){
            if (httpSession.getAttribute("admin") == null){
                response.getWriter().println("FORBIDDEN");
                return;
            }
        } else if (url.startsWith("/cm")){
            if (httpSession.getAttribute("cm") == null){
                response.getWriter().println("FORBIDDEN");
                return;
            }
        } else if (url.startsWith("/visitor")){
            if (httpSession.getAttribute("visitor") == null){
                response.getWriter().println("FORBIDDEN");
                return;
            }
        } else if (url.startsWith("/drugstore") || url.startsWith("/ds")){
            if (httpSession.getAttribute("ds") == null){
                response.getWriter().println("FORBIDDEN");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
