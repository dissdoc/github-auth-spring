package com.dissdoc.github.security;

import com.dissdoc.github.domain.model.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by KMukhov on 22.07.2015.
 */
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        checkSavedRequests(request, response);

        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        Account account = principal.getAccount();
        response.setContentType("application/json;charset=UTF-8");
        String json = String.format("{\"id\":%s, \"firstName\":\"%s\", \"lastName\":\"%s\", \"username\":\"%s\"}",
                account.getId(), account.getFirstName(), account.getLastName(), account.getUsername());
        Writer writer = response.getWriter();
        writer.write(json);
        writer.close();
    }

    private void checkSavedRequests(HttpServletRequest request, HttpServletResponse response) {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest == null) {
            clearAuthenticationAttributes(request);
            return;
        }
        String targetUrlParam = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParam != null &&
                StringUtils.hasText(request.getParameter(targetUrlParam)))) {
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
            return;
        }

        clearAuthenticationAttributes(request);
    }
}
