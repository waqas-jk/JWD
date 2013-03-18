package org.jwd.assignment.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.jwd.assignment.Constants;
import org.jwd.assignment.model.Role;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleDaoTest extends BaseDaoTestCase {
    @Autowired
    private RoleDao dao;

    @Test
    public void testGetRoleInvalid() throws Exception {
        Role role = dao.getRoleByName("badrolename");
        assertNull(role);
    }

    @Test
    public void testGetRole() throws Exception {
        Role role = dao.getRoleByName(Constants.NORMAL_ROLE);
        assertNotNull(role);
    }

    @Test
    public void testUpdateRole() throws Exception {
        Role role = dao.getRoleByName("ROLE_NORMAL");
        dao.save(role);
        flush();
        
        role = dao.getRoleByName("ROLE_NORMAL");
    }

    @Test
    public void testAddAndRemoveRole() throws Exception {
        Role role = new Role("testrole");
        dao.save(role);
        flush();
        
        role = dao.getRoleByName("testrole");

        dao.removeRole("testrole");
        flush();

        role = dao.getRoleByName("testrole");
        assertNull(role);
    }

}
