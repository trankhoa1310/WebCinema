package com.vn.cines_start.Service;

import java.util.List;
import java.util.Optional;

import com.vn.cines_start.Model.Role;

public interface IRoleService {
	List<Role> getAllRole();
    Optional<Role> findRoleById(Long id);
}
