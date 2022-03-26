package com.commerce.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.commerce.util.PasswordUtils;

/**
 * @author japhet
 * Entity implementation class for Entity: User
 *
 */
@Entity(name = "User")
@Table(name="t_user")
@NamedQueries({
	@NamedQuery(name = User.FIND_BY_EMAIL, query = "SELECT person FROM User person WHERE person.email = :email"),
	@NamedQuery(name = User.FIND_BY_LOGIN, query = "SELECT person FROM User person WHERE person.login = :login"),
	@NamedQuery(name = User.FIND_BY_UUID, query = "SELECT person FROM User person WHERE person.uuid = :uuid"),
	@NamedQuery(name = User.FIND_BY_LOGIN_PASSWORD, query = "SELECT person FROM User person WHERE person.login = :login AND person.password = :password"),
	@NamedQuery(name = User.FIND_ALL, query = "SELECT subscribers FROM User subscribers")	
})
public class User implements Serializable {
	// =================================
	// =        Constants              =
	// =================================

	public static final String FIND_BY_EMAIL = "User.findByEmail";
	public static final String FIND_BY_LOGIN = "User.findByLogin";
	public static final String FIND_BY_UUID = "User.findByUUID";
	public static final String FIND_BY_LOGIN_PASSWORD = "User.findByLoginAndPassword";
	public static final String FIND_ALL = "User.findAll";
	
	// =================================
	// = 	   Attributes/fields       =
	// =================================
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Version
	@Column(name = "version")
	private Integer version;
	
	@NotNull
	@Size(min = 2, max = 50)
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	
	@NotNull
	@Size(min = 2, max = 50)
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
	
	@Column
	private String telephone;
	
	@NotNull
	@Email
	@Column
	private String email;
	
	@NotNull
	@Size(min = 1, max = 12)
	@Column(nullable = false, length = 12)
	private String login;
	
	@NotNull
	@Size(min = 1, max = 256)
	@Column(nullable = false, length = 256)
	private String password;
	
	@Size(min = 1, max = 256)
	@Column(length = 256)
	private String uuid;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "user_role")
	private UserRole role;
	
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	private static final long serialVersionUID = 1L;
	
	// =================================
	// =        Lifecycle methods      =
	// =================================
	
	@PrePersist
	@PreUpdate
	private void digestPassword() {
		password = PasswordUtils.digestPassword(password);
	}
	
	// =================================
	// =         Constructors          =
	// =================================

	public User() {
		super();
	} 
	
	// =================================
	// = 		Getters and Setters    =
	// =================================
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}   
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}   
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}   
	public UserRole getRole() {
		return this.role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}   
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	// ========================================
	// = Method hash, equals, toString        =
	// ========================================

	@Override
	public String toString() {
		return "User [id=" + id + ", version=" + version + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", telephone=" + telephone + ", email=" + email + ", login=" + login + ", password=" + password
				+ ", uuid=" + uuid + ", role=" + role + ", dateOfBirth=" + dateOfBirth + "]";
	}
   
}
