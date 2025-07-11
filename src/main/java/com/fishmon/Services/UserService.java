package com.fishmon.Services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fishmon.Model.Dao.User;
import com.fishmon.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    // Register user dengan enkripsi password
    public User registerUser(User user) throws Exception {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("Username sudah digunakan");
        }

        // Enkripsi password sebelum disimpan
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

      public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
