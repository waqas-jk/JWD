package org.jwd.assignment.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.jwd.assignment.Constants;
import org.jwd.assignment.model.Role;
import org.jwd.assignment.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends BaseDaoTestCase {
    @Autowired
    private UserDao dao;
    @Autowired
    private RoleDao rdao;

    @Test
    public void testGetUserInvalid() throws Exception {
        // should throw DataAccessException
        dao.get(1000L);
    }

    @Test
    public void testGetUser() throws Exception {
        User user = dao.get(-1L);

        assertNotNull(user);
        assertTrue(user.getAuthority() != null);
        assertTrue(user.isEnabled());
    }

    @Test
    public void testGetUserPassword() throws Exception {
        User user = dao.get(-1L);
        String password = dao.getUserPassword(user.getId());
        assertNotNull(password);
        log.debug("password: " + password);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = dao.get(-1L);

        dao.saveUser(user);
        flush();


        // verify that violation occurs when adding new user with same username
        User user2 = new User();
        user2.setConfirmPassword(user.getConfirmPassword());
        user2.setEmail(user.getEmail());
        user2.setFirstName(user.getFirstName());
        user2.setLastName(user.getLastName());
        user2.setPassword(user.getPassword());
        user2.setAuthority(user.getAuthority());
        user2.setUsername(user.getUsername());

        // should throw DataIntegrityViolationException
        dao.saveUser(user2);
    }

    @Test
    public void testAddAndRemoveUser() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpass");
        user.setFirstName("Test");
        user.setLastName("Last");
        user.setEmail("testuser@gmail.org");

        Role role = rdao.getRoleByName(Constants.NORMAL_ROLE);
        assertNotNull(role.getId());
        user.setAuthority(role);

        user = dao.saveUser(user);
        flush();

        assertNotNull(user.getId());
        user = dao.get(user.getId());
        assertEquals("testpass", user.getPassword());

        dao.remove(user);
        flush();

        // should throw DataAccessException
        dao.get(user.getId());
    }

    @Test
    public void testUserExists() throws Exception {
        boolean b = dao.exists(-1L);
        assertTrue(b);
    }

    @Test
    public void testUserNotExists() throws Exception {
        boolean b = dao.exists(111L);
        assertFalse(b);
    }

}
