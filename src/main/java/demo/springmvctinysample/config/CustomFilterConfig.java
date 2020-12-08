package demo.springmvctinysample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@Configuration
@Slf4j
public class CustomFilterConfig {

    @Bean
    @Primary
    public FilterRegistrationBean<LoggingFilter> getFilterRegistrationBean() {
        FilterRegistrationBean<LoggingFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new LoggingFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

    private static class LoggingFilter implements Filter {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
            if (request instanceof HttpServletRequest) {
                HttpServletRequest req = ((HttpServletRequest) request);
                ArrayList<String> headers = Collections.list(req.getHeaderNames());
                for (String header : headers) {
                    log.info("le header {} vaut {}", header, req.getHeader(header));
                }
            }
            chain.doFilter(request, response);
        }

        @Override
        public void init(FilterConfig arg) {
            // nothing to do
        }

        @Override
        public void destroy() {
            // nothing to do
        }
    }
}
