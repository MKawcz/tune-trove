package com.tunetrove.service;

import com.tunetrove.dto.UserDto;
import com.tunetrove.exception.UserNotFoundException;
import com.tunetrove.mapper.UserMapper;
import com.tunetrove.model.User;
import com.tunetrove.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toDtoFromEntity(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDtoFromEntity).toList();
    }

    public UserDto updateUser(long id, UserDto updatedUserDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // 2. Zaktualizuj pola użytkownika na podstawie dostarczonego updatedUserDto.
        userMapper.updateUserFromDto(updatedUserDto, existingUser);

        // 3. Zapisz zaktualizowanego użytkownika w bazie danych.
        User savedUser = userRepository.save(existingUser);

        // 4. Zwróć zaktualizowanego użytkownika w formie UserDto.
        return userMapper.toDtoFromEntity(savedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

}
