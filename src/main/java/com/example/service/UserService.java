package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.model.Users;

public interface UserService {
	 List<Users> getAllUsers();
	 void saveUsers(Users user);
	 Users getUsersById(long id);
	 void deleteUserById(long id);
	 Page<Users> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
		
}
