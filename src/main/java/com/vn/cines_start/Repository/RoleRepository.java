package com.vn.cines_start.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.cines_start.Model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
