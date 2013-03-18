package org.jwd.assignment.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jwd.assignment.Constants;
import org.jwd.assignment.model.Role;
import org.jwd.assignment.model.User;
import org.jwd.assignment.service.UserExistsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;

import com.opensymphony.xwork2.Preparable;

/**
 * Action for  User.
 */
public class UserAction extends BaseAction implements Preparable {
    private static final long serialVersionUID = 6776558938712115191L;
    private List<User> users;
    private User user;
    private String id;

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
        	
        	if(StringUtils.isNotEmpty(getRequest().getParameter("user.id"))){
        		user = userManager.getUser(getRequest().getParameter("user.id"));
        	}else{
        		user = new User();
        	}
            
            user.setFirstName(getRequest().getParameter("user.firstName"));
            user.setLastName(getRequest().getParameter("user.lastName"));
            user.setUsername(getRequest().getParameter("user.username"));
            user.setEmail(getRequest().getParameter("user.email"));
            
            user.setPassword(getRequest().getParameter("user.password"));
            user.setConfirmPassword(getRequest().getParameter("user.confirmPassword"));
        }
    }

    /**
     * Holder for users to display on list screen
     *
     * @return list of users
     */
    public List<User> getUsers() {
        return users;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Delete the user passed in.
     *
     * @return success
     */
    public String delete() {
        userManager.removeUser(user.getId().toString());
        List<Object> args = new ArrayList<Object>();
        args.add(user.getFullName());
        saveMessage(getText("user.deleted", args));

        return SUCCESS;
    }
    
    

    /**
     * Logout the user
     *
     * @return success
     */
    public String logout() {
    	
    	HttpSession session = getRequest().getSession(false); 
    	if (session != null) {
    		session.invalidate();
    	}

        return SUCCESS;
    }

    /**
     * Grab the user from the database based on the "id" passed in.
     *
     * @return success if user found
     */
    public String edit() {

        id = getRequest().getParameter("id");
        
        if (StringUtils.isNotEmpty(id)) {
            user = userManager.getUser(id);
        } else {
            user = new User();
            user.setAuthority(new Role(Constants.NORMAL_ROLE));
        }

        //set the confirm password.
        if (user.getUsername() != null) {
            user.setConfirmPassword(user.getPassword());
        }

        return SUCCESS;
    }

    /**
     * Default: just returns "success"
     *
     * @return "success"
     */
    public String execute() {
        return SUCCESS;
    }

    /**
     * Sends users to "mainMenu"
     *
     * @return "mainMenu" or "cancel"
     */
    public String cancel() {
        return "cancel";
    }

    /**
     * Save user
     *
     * @return success if everything worked, otherwise input
     * @throws Exception when setting "access denied" fails on response
     */
    public String save() throws Exception {

        if (getRequest().isUserInRole(Constants.ADMIN_ROLE)) {
            String userRole = getRequest().getParameter("userRole");
            
            if(userRole != null){
            	try{
                   user.setAuthority(roleManager.getRole(userRole));
                } catch (DataIntegrityViolationException e) {
                    return showUserExistsException();
                }
            }
        }
        
        try {
        	
            userManager.saveUser(user);
        } catch (AccessDeniedException ade) {
            log.warn(ade.getMessage());
            getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        } catch (UserExistsException e) {
            return showUserExistsException();
        }

        if (user.getUsername() != null) {
            user.setConfirmPassword(user.getPassword());
        }
        
        return SUCCESS;
    }

    private String showUserExistsException() {
        List<Object> args = new ArrayList<Object>();
        args.add(user.getUsername());
        args.add(user.getEmail());
        addActionError(getText("errors.existing.user", args));

        user.setPassword(user.getConfirmPassword());
        return INPUT;
    }

    /**
     * Fetch all users from database and put into local "users" variable for retrieval in the UI.
     *
     * @return "success" if no exceptions thrown
     */
    public String list() {
        users = userManager.getUsers();
        return SUCCESS;
    }
    
  

}
