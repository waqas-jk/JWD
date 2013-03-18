package org.jwd.assignment.service;

import java.util.List;

import org.jwd.assignment.model.User;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
public interface UserService {
    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId
     * @return User
     */
    User getUser(String userId);

    /**
     * Finds a user by their username.
     *
     * @param username 
     * @return
     */
    User getUserByUsername(String username);

    /**
     * Retrieves a list of all users.
     *
     * @return List
     */
    List<User> getUsers();

    /**
     * Saves a user's information
     *
     * @param user
     * @return updated user
     * @throws UserExistsException thrown when user already exists
     */
    User saveUser(User user) throws UserExistsException;

    /**
     * Removes a user from the database by their userId
     *
     * @param userId
     */
    void removeUser(String userId);
}
