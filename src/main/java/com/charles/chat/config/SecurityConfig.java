package com.charles.chat.config;

import com.charles.chat.helper.ApiLogSaver;
import com.charles.chat.helper.ChatLogSummary;
import com.charles.chat.model.ApiLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthFilter authFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .httpBasic().disable()
                .addFilterAfter(new AuthFilter(), BasicAuthenticationFilter.class);
    }

    @Component
    private static class AuthFilter extends OncePerRequestFilter {



        ApiLogSaver apiLogSaver = ApiLogSaver.getInstance();
        public AuthFilter() {
            this.sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
//            this.sdf.setTimeZone(TimeZone.getTimeZone("UTF+8"));
        }
        SimpleDateFormat sdf;
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            if (!request.getRequestURI().equals("/api/chat/api/log")) {
                String host = request.getHeader("X-Real-IP") == null? request.getRemoteHost() : request.getHeader("X-Real-IP");
                String agent = request.getHeader("X-User-Agent") + ":" + request.getHeader("User-Agent");
//                System.out.println(new Date());
                apiLogSaver.getApiLogs().add(
                        new ApiLog()
                                .setHost(host)
                                .setPath(request.getRequestURI())
                                .setDevice(agent)
                                .setAccessAt(sdf.format(new Date()))
                                .setQuery(request.getQueryString())
                );
            }
            filterChain.doFilter(request, response);

        }

    }


}
