package org.jwd.assignment.webapp.interceptor;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.access.AccessDeniedException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Correctly report spring-security's AccessDeniedException thrown from within Struts actions as 403 error.
 * These exceptions can be fired in a call to the service layer, for instance.
 * 
 */
public class AccessDeniedInterceptor implements Interceptor {

	private static final long serialVersionUID = -4505641043176830381L;

	@Override
    public String intercept(ActionInvocation invocation) throws Exception {
        try {
            return invocation.invoke();
        } catch (AccessDeniedException e) {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    /**
     * This method currently does nothing.
     */
    @Override
    public void destroy() {
    }

    /**
     * This method currently does nothing.
     */
    @Override
    public void init() {
    }
}
