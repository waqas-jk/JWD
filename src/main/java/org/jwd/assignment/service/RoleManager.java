package org.jwd.assignment.service;

import java.util.List;

import org.jwd.assignment.model.Role;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 */
public interface RoleManager extends GenericManager<Role, Long> {
    /**
     * {@inheritDoc}
     */
    List getRoles(Role role);

    /**
     * {@inheritDoc}
     */
    Role getRole(String rolename);

    /**
     * {@inheritDoc}
     */
    Role saveRole(Role role);

    /**
     * {@inheritDoc}
     */
    void removeRole(String rolename);
    
    public List<Role> getAllRoles();
}
