package com.hgebk.dokobackend.config;

import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

public class CustomRequestLogFilter extends AbstractRequestLoggingFilter {
    public CustomRequestLogFilter() {
    }

    protected boolean shouldLog(HttpServletRequest request) {
        return this.logger.isInfoEnabled();
    }

    protected void beforeRequest(HttpServletRequest request, String message) {
        this.logger.info("============================================================================================");
        this.logger.info(message);
    }

    protected void afterRequest(HttpServletRequest request, String message) {
        this.logger.info(message);
        this.logger.info("============================================================================================");
    }
}
