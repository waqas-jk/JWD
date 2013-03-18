package org.jwd.assignment.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;

/**
 * This class is used to represent available roles in the database.
 *
 */
@Entity
@Table(name = "role")
public class Role extends BaseObject implements Serializable, GrantedAuthority {
    private static final long serialVersionUID = 3690197650654049848L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length = 20)
    private String name;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Role() {
    }
    
    /**
     * Create a new instance and set the name.
     *
     * @param name name of the role.
     */
    public Role(final String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }
	
	
	public void setId(Long id) {
	    this.id = id;
	}

    /**
     * @return the name property (getAuthority required by Acegi's GrantedAuthority interface)
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    @Transient
    public String getAuthority() {
        return getName();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }

        final Role role = (Role) o;

        return !(name != null ? !name.equals(role.name) : role.name != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (name != null ? name.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.name)
                .toString();
    }
}
