package com.example.demo.usuario;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public interface UserService {

    public UserDto save(@Valid UserDto userDto);     
    public UserDto findById(Long id);
    public UserDto update(Long id, UserDto userDto);
    public void delete(Long id);
    public List<UserDto> getAll();        
}

