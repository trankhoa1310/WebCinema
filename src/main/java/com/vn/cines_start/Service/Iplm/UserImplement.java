package com.vn.cines_start.Service.Iplm;

import com.vn.cines_start.Model.User;
import com.vn.cines_start.Repository.UserRepository;
import com.vn.cines_start.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImplement implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User edit(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
    userRepository.deleteById(id);
    }

	@Override
	public Optional<User> getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findUserByEmail(email);
	}

	@Override
	public User findAllByEmail(String email) {
		return userRepository.findAllByEmail(email);
	}

	@Override
	public List<User> findUserByRole(Long roleId) {
		return userRepository.findUserByRole(roleId);
	}

}
