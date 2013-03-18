package org.jwd.assignment.webapp.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jwd.assignment.Constants;
import org.jwd.assignment.service.RoleManager;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * <p>StartupListener class used to initialize and database settings
 * and populate any application-wide drop-downs.
 * <p/>
 * <p>Keep in mind that this listener is executed outside of OpenSessionInViewFilter,
 * so if you're using Hibernate you'll have to explicitly initialize all loaded data at the
 * GenericDao or service level to avoid LazyInitializationException. Hibernate.initialize() works
 * well for doing this.
 *
 */
public class StartupListener implements ServletContextListener {
    private static final Log log = LogFactory.getLog(StartupListener.class);

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public void contextInitialized(ServletContextEvent event) {
        log.debug("Initializing context...");

        ServletContext context = event.getServletContext();

        // Orion starts Servlets before Listeners, so check if the config
        // object already exists
        Map<String, Object> config = (HashMap<String, Object>) context.getAttribute(Constants.CONFIG);

        if (config == null) {
            config = new HashMap<String, Object>();
        }

        ApplicationContext ctx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(context);

        PasswordEncoder passwordEncoder = (PasswordEncoder) ctx.getBean("passwordEncoder");;

        context.setAttribute(Constants.CONFIG, config);

        // output the retrieved values for the Init and Context Parameters
        if (log.isDebugEnabled()) {
            if (passwordEncoder != null) {
                log.debug("Password Encoder: " + passwordEncoder.getClass().getSimpleName());
            }
            log.debug("Populating drop-downs...");
        }

        setupContext(context);
    }

    /**
     * This method uses the LookupManager to lookup available roles from the data layer.
     *
     * @param context The servlet context
     */
    public static void setupContext(ServletContext context) {
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        RoleManager mgr = (RoleManager) ctx.getBean("roleManager");

        // get list of possible roles
        context.setAttribute(Constants.AVAILABLE_ROLES, mgr.getAllRoles());
        log.debug("Drop-down initialization complete [OK]");
    }

    /**
     * Shutdown servlet context (currently a no-op method).
     *
     * @param servletContextEvent The servlet context event
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //LogFactory.release(Thread.currentThread().getContextClassLoader());
        //Commented out the above call to avoid warning when SLF4J in classpath.
        //WARN: The method class org.apache.commons.logging.impl.SLF4JLogFactory#release() was invoked.
        //WARN: Please see http://www.slf4j.org/codes.html for an explanation.
    }
}
