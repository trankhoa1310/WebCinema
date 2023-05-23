package com.vn.cines_start.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.vn.cines_start.Model.Role;
import com.vn.cines_start.Model.Schedule;
import com.vn.cines_start.Model.Ticket;
import com.vn.cines_start.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
	private Long id;
    private String firstName;
    private String lastName;

    private String email;
    private String password;
    private String phone;

    private List<Long> roleIds;

  
}
