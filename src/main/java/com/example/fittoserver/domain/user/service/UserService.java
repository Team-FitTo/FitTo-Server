package com.example.fittoserver.domain.user.service;

import com.example.fittoserver.domain.user.entity.UserEntity;
import com.example.fittoserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public Optional<UserEntity> getAdmin() {
        return userRepository.findByRole("ROLE_ADMIN");
    }
}