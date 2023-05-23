package com.vn.cines_start.Service.Iplm;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vn.cines_start.Model.Role;
import com.vn.cines_start.Repository.RoleRepository;
import com.vn.cines_start.Service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleRepository roleRepository;

	@Override
	public List<Role> getAllRole() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findRoleById(Long id) {
		return roleRepository.findById(id);
	}

 

}
