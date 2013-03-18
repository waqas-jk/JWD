package org.jwd.assignment.service;

import java.util.List;

import org.jwd.assignment.dao.UserDao;
import org.jwd.assignment.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 */
public interface UserManager extends GenericManager<User, Long> {
    /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setUserDao(UserDao userDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId 
     * @return User
     */
    User getUser(String userId);

    /**
     * Finds a user by their username.
     * @param username 
     * @return User object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
     *         exception thrown when user not found
     */
    User getUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<User> getUsers();

    /**
     * Saves a user's information.
     *
     * @param user
     * @throws UserExistsException thrown when user already exists
     * @return user
     */
    User saveUser(User user) throws UserExistsException;

    /**
     * Removes a user from the database
     *
     * @param user
     */
    void removeUser(User user);

    /**
     * Removes a user from the database by their userId
     *
     * @param userId
     */
    void removeUser(String userId);

}
