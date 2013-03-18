package org.jwd.assignment.dao;

import java.util.List;

import org.jwd.assignment.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User Data Access Object (GenericDao) interface.
 *
 */
public interface UserDao extends GenericDao<User, Long> {

    /**
     * Gets users information based on login name.
     * @param username 
     * @return userDetails populated userDetails object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException thrown when user not
     * found in database
     */
    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Gets a list of users ordered by the uppercase version of their username.
     *
     * @return List
     */
    List<User> getUsers();

    /**
     * Saves a user's information.
     * @param user 
     * @return the persisted User object
     */
    User saveUser(User user);

    /**
     * Retrieves the password in DB for a user
     * @param userId
     * @return the password in DB
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    String getUserPassword(Long userId);
    
}
