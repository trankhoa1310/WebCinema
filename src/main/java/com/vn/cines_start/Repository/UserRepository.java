package com.vn.cines_start.Repository;

import com.vn.cines_start.Model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findUserByEmail(String email);
	
	@Query(value = "select * from Users s where s.email = ?1 ", nativeQuery = true)
	User findAllByEmail(String email);
	
	
	@Query(value = "select u.* from Users u join user_role as ur on u.id=ur.user_id where ur.role_id = ?1", nativeQuery = true)
	List<User> findUserByRole(Long roleId);
}
