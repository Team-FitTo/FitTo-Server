package com.example.fittoserver.domain.user.service;

import com.example.fittoserver.domain.user.entity.UserEntity;
import com.example.fittoserver.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
}