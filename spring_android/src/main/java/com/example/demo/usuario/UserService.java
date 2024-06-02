package com.example.demo.usuario;

import java.util.List;

import com.example.demo.hijo.HijoDto;

import jakarta.validation.Valid;


public interface UserService {

    public UserDto save(@Valid UserDto userDto);     
    public UserDto findById(Long id);
    public UserDto update(Long id, UserDto userDto);
    public void delete(Long id);
    public HijoDto getHijo(Long id);
    public List<UserDto> getAll();
    public RespuestaLogin login(String email, String password);
}

