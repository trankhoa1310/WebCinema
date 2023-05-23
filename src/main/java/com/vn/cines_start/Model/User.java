package com.vn.cines_start.Model;


import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor

@Getter
@Setter
@Table(name = "users")
public class User {
	 public User(){}

	    public User(User user) {
	        this.firstName = user.getFirstName();
	        this.lastName = user.getLastName();
	        this.email = user.getEmail();
	        this.password = user.getPassword();
	        this.roles = user.getRoles();
	        this.phone = user.getPhone();
	    }


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String firstName;
    
    private String lastName;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(unique = true, nullable = false)
    private String phone;
    
    private String password;
    @ManyToMany(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
       name="user_role",
       joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
       inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private List<Role> roles;

    @OneToMany(mappedBy = "users")
    private Set<Ticket> tickets;
    
    @OneToMany(mappedBy = "users")
    private Set<BillUser> billusers;

}

