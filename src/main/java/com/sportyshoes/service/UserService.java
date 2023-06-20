package com.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.User;
import com.sportyshoes.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getUserList() {
		System.out.println(userRepository.findAll().toString());
		return (List<User>) userRepository.findAll();
	}

	public User addUser(User user) {
		System.out.println("Save User");
		return userRepository.save(user);
	}

	public void deleteByEmailId(String emailId) {
		userRepository.deleteById(emailId);
	}

	public User getUser(String emailId) {
		return userRepository.findById(emailId).get();
	}

	public User getUser(String emailId, String password) {
		return userRepository.findByEmailIdAndPassword(emailId, password);
	}

	public void updateUser(User user) {
		System.out.println(user.getEmailId() + user.getFirstName());
		if (userRepository.findById(user.getEmailId()).get() != null)

			userRepository.save(user);
		System.out.println(user.getEmailId() + user.getFirstName());

	}

}
