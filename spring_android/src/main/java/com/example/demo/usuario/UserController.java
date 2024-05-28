package com.example.demo.usuario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.hijo.HijoDto;
import com.example.demo.hijo.HijoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    HijoService hijoService;
    @Autowired
    UserService userService;

    @PostMapping("/crear")
    public ResponseEntity<UserDto> crearUsuario(@RequestBody UserDto user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping("/actualizar/{id}")
    public ResponseEntity<UserDto> updateUsuario(@RequestBody UserDto user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
    @GetMapping("/obtenerUsuario")
    public ResponseEntity<UserDto> getUsuario(@PathVariable String id) {
        UserDto user = userService.findById(Long.parseLong(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/obtenerHijo")
    public  ResponseEntity<HijoDto>getHijo (@PathVariable String id) {
        List<HijoDto> hijos = hijoService.getByIdPadre(Long.parseLong(id));
        HijoDto hijo = hijos.get(0);
        return new ResponseEntity<>(hijo, HttpStatus.OK);
    }
    
    }       
    


