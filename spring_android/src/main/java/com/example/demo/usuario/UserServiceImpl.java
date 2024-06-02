package com.example.demo.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demo.hijo.Hijo;
import com.example.demo.hijo.HijoDto;
import com.example.demo.hijo.HijoRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Validated
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HijoRepo hijoRepo;

    @Override
    @Transactional
    public UserDto save(@Valid UserDto userDto) {
        Usuario userEntity;
        if (userDto.getId() == 0) {
            userDto.setId(null);
        }
        if (userDto.getId() != null) {
            userEntity = userRepo.findById(userDto.getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            modelMapper.map(userDto, userEntity);
        } else {
            userEntity = modelMapper.map(userDto, Usuario.class);
        }

        userEntity = userRepo.save(userEntity);

        if (userDto.getHijos() != null && !userDto.getHijos().isEmpty()) {
            for (HijoDto hijoDto : userDto.getHijos()) {
                Hijo hijo = modelMapper.map(hijoDto, Hijo.class);
                hijo.setPadre(userEntity);
                hijoRepo.save(hijo);
            }
        }

        userEntity = userRepo.findById(userEntity.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto findById(Long id) {

        Usuario userEntity = userRepo.getReferenceById(id);
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {

        userRepo.deleteById(id);

    }

    @Override
    public List<UserDto> getAll() {
        List<Usuario> listaUsuarios = userRepo.findAll();
        List<UserDto> listaUserDtos = listaUsuarios.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return listaUserDtos;
    }

    @Override
    public HijoDto getHijo(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHijo'");
    }

    @Override
    public RespuestaLogin login(String email, String password) {
        Usuario usuario = userRepo.login(email, password);
        if (usuario != null) {
            return new RespuestaLogin(true, usuario.getId());
        } else {
            return new RespuestaLogin(false, null);
        }
    }

}
