package org.jwd.assignment.model;

import java.io.Serializable;


/**
 * Base class for Model objects. Child objects should implement toString(),
 * equals() and hashCode().
 * 
 */
public abstract class BaseObject implements Serializable {    

	 /**
	 * 
	 */
	private static final long serialVersionUID = 7855263252114085773L;
	 
    /**
     * Returns a multi-line String with key=value pairs.
     * @return 
     */
    public abstract String toString();

    /**
     * Compares object equality. When using Hibernate, the primary key should
     * not be a part of this comparison.
     * @param o 
     * @return true/false based on equality tests
     */
    public abstract boolean equals(Object o);

    /**
     * @return hashCode
     */
    public abstract int hashCode();
}
