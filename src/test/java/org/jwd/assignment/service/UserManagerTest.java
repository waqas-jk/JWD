package org.jwd.assignment.service;

import static org.junit.Assert.assertNotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.jwd.assignment.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserManagerTest extends BaseManagerTestCase {
    private Log log = LogFactory.getLog(UserManagerTest.class);
    @Autowired
    private UserManager mgr;

    private User user;

    @Test
    public void testGetUser() throws Exception {
        user = mgr.getUserByUsername("user");
        assertNotNull(user);
        
        log.debug(user);
        assertNotNull(user.getAuthority());
    }

    @Test
    public void testSaveUser() throws Exception {
        user = mgr.getUserByUsername("user");

        log.debug("saving user with updated phone number: " + user);

        user = mgr.saveUser(user);
        assertNotNull(user.getAuthority());
    }

}
