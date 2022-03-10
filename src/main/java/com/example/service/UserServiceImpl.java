package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.example.model.Users;
import com.example.repository.UserRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Service
public abstract class UserServiceImpl implements UserService{
	
    @Autowired
    private UserRepository userRepository;

    @Override
    public List < Users > getAllUsers(){
        return userRepository.findAll();
    }
    @Override
    public void saveUsers(Users user) {
        this.userRepository.save(user);
    }

    @Override
    public Users getUsersById(long id) {
        Optional < Users > optional = userRepository.findById(id);
        Users user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException(" User not found for id :: " + id);
        }
        return user;
    }
    @Override
    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }
    
    
/**
    @Override
    public Page < Users > findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
            Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.userRepository.findAll(pageable);
    }
    **/
}
