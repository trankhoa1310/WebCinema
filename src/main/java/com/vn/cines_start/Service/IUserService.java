package com.vn.cines_start.Service;

import com.vn.cines_start.Model.Movie;
import com.vn.cines_start.Model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();

    Optional<User> findUserById(Long id);
    User create(User user);

    User edit(User user);

    void delete(Long id);
    
    Optional<User> getUserByEmail(String email);

    User findAllByEmail(String email);
    
	List<User> findUserByRole(Long roleId);
}
