package org.jwd.assignment.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jwd.assignment.dao.RoleDao;
import org.jwd.assignment.model.Role;
import org.springframework.stereotype.Repository;


/**
 * This class interacts with hibernate session to save/delete and
 * retrieve Role objects.
 *
 */
@Repository
public class RoleDaoHibernate extends GenericDaoHibernate<Role, Long> implements RoleDao {

    public RoleDaoHibernate() {
        super(Role.class);
    }

    /**
     * {@inheritDoc}
     */
    public Role getRoleByName(String rolename) {
        List roles = getSession().createCriteria(Role.class).add(Restrictions.eq("name", rolename)).list();
        if (roles.isEmpty()) {
            return null;
        } else {
            return (Role) roles.get(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeRole(String rolename) {
        Object role = getRoleByName(rolename);
        Session session = getSessionFactory().getCurrentSession();
        session.delete(role);
    }
    
    @SuppressWarnings("unchecked")
    public List<Role> getRoles() {
        log.debug("Retrieving all role names...");
        return getSession().createCriteria(Role.class).list();
    }
}
