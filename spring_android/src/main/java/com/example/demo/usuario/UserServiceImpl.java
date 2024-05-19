package com.example.demo.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demo.hijo.HijoDto;
import com.example.demo.hijo.HijoRepo;

import jakarta.validation.Valid;

@Service
@Validated
public class UserServiceImpl implements UserService  {
    @Autowired
    private UserRepo userRepo;   
   
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HijoRepo hijoRepo;
   
    

    @Override
    public UserDto save(@Valid UserDto userDto) {
        
        Usuario userEntity = modelMapper.map(userDto, Usuario.class);
        if (userDto.getId() == null) {
            List<Long> ids = new ArrayList<Long>();
            for (HijoDto hijo : userDto.getHijos()) {
                ids.add(hijo.getId());
            }
            userEntity.setHijos(hijoRepo.findManyById(ids));
        }
        userEntity = userRepo.save(userEntity);        
        return modelMapper.map(userEntity,UserDto.class);
       
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

   
     
     }




