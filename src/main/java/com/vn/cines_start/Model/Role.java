package com.vn.cines_start.Model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@javax.persistence.Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@javax.persistence.Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, unique = true)

	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;
}
