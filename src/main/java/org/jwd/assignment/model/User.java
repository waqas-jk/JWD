package org.jwd.assignment.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * This class represents the basic "user" object that allows for authentication
 * and user management.  It implements Acegi Security's UserDetails interface.
 *
 */
@Entity
@Table(name = "users")
public class User extends BaseObject implements Serializable, UserDetails {
    private static final long serialVersionUID = 3832626162173359411L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false, length = 50, unique = true)
    private String username;        
	
	@Column(nullable = false)
    private String password;
	
	@Transient
    private String confirmPassword;
    
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;       
    
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    
    @Column(nullable = false, unique = true)
    private String email;  
    
    @ManyToOne
   	@JoinColumn(name = "ROLE_ID", nullable = false)
    private Role authority;
    
    @Column
    @Type(type = "true_false")
    private boolean enabled = true;
    
    
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public User() {
    }

    public Long getId() {
        return id;
    }
	
	public void setId(Long id) {
	    this.id = id;
	}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Returns the full name.
     *
     * @return firstName + ' ' + lastName
     */
    @Transient
    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    public Role getAuthority() {
        return authority;
    }

    /**
     * @return GrantedAuthority[] an array of authority.
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Transient
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
        authorities.add(authority);
        return authorities;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     * @return true if account is still active
     */
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     * @return false if account is locked
     */
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     * @return true if credentials haven't expired
     */
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthority(Role authority) {
        this.authority = authority;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        final User user = (User) o;

        return !(username != null ? !username.equals(user.getUsername()) : user.getUsername() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (username != null ? username.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("username", this.username);

        if (authority != null) {
            sb.append("Granted Authorities: ");
            sb.append(authority.toString());
        } else {
            sb.append("No Granted Authorities");
        }
        return sb.toString();
    }

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
