package com.dissdoc.github.security;

import com.dissdoc.github.domain.model.Account;
import com.dissdoc.github.domain.service.AccountService;
import org.springframework.web.filter.GenericFilterBean;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by KMukhov on 22.07.2015.
 */
public class RegistrationFilter extends GenericFilterBean {

    public static final String FILTER_PROCESS_URL = "/registration";

    @Inject
    private AccountService accountService;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!requiresProcess(httpRequest, httpResponse)) {
            filterChain.doFilter(request, response);
            return;
        }

        Account account = new Account(httpRequest.getParameter("username"),
                httpRequest.getParameter("password"),
                httpRequest.getParameter("firstName"),
                httpRequest.getParameter("lastName"));
        accountService.create(account);

        sendResponseResult(httpResponse, 200, "OK");
    }

    protected boolean requiresProcess(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();

        if ("".equals(request.getContextPath())) {
            return uri.startsWith(FILTER_PROCESS_URL);
        }

        return uri.startsWith(request.getContextPath() + FILTER_PROCESS_URL);
    }

    private void sendResponseResult(HttpServletResponse response, int status,
                                    String errorCode) throws IOException {
        response.setStatus(status);
        response.getWriter().write(errorCode);
    }
}
