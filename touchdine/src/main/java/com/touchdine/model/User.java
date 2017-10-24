package com.touchdine.model;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
 
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Bean class used for holding the user information.
 * @author ravisha
 *
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
 
    @Column(name = "first_name")
    private String firstName;
 
    @Column(name = "last_name")
    private String lastName;
 
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "accsess_key")
    private String accessKey;
    
    @Column(name = "sec_accsess_key")
    private String secAccessKey;
 
    @Column(name = "created")
    @CreationTimestamp
    private Date created;
 
    @Column(name = "updated")
    @UpdateTimestamp
    private Date updated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecAccessKey() {
		return secAccessKey;
	}

	public void setSecAccessKey(String secAccessKey) {
		this.secAccessKey = secAccessKey;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
    
 
    
}
 