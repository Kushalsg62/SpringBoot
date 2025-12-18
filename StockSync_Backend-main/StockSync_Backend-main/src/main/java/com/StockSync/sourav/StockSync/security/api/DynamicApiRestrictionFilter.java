package com.StockSync.sourav.StockSync.security.api;

import com.StockSync.sourav.StockSync.config.BlockedApiConfig;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class DynamicApiRestrictionFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(DynamicApiRestrictionFilter.class);
    private final BlockedApiConfig blockedApiConfig;

    public DynamicApiRestrictionFilter(BlockedApiConfig blockedApiConfig) {
        this.blockedApiConfig = blockedApiConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        List<String> blockedApis = blockedApiConfig.getApis();

        logger.info("Checking API restriction for: " + requestURI);

        if (blockedApis.contains(requestURI)) {
            logger.info("API is blocked: " + requestURI);

            // Immediately return 503 without proceeding further
            httpResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write("{\"status\": 503, \"message\": \"This API is temporarily unavailable.\"}");
            return; // Stop further execution
        }

        // Proceed with normal execution if the API is not blocked
        chain.doFilter(request, response);
    }
}
