package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserEntity getUser(Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UserNotFoundException(id);
    }

    public List<UserEntity> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public UserEntity addUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public void deleteUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    public void updateUser(Long id, UserEntity userEntityDetails) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            userEntity.setName(userEntityDetails.getName());
            userRepository.save(userEntity);
        } else {
            throw new UserNotFoundException(id);
        }
    }
}
