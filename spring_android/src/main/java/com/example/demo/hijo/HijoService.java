package com.example.demo.hijo;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;


public interface HijoService {
    public HijoDto save(@Valid HijoDto Hijo);
    public HijoDto get(Long id);
    public List<HijoDto> getAll();
    public List<HijoDto> getMany(List<Long> ids);
    public List<HijoDto> getByIdPadre(Long id);
    public void delete(Long id);
}
