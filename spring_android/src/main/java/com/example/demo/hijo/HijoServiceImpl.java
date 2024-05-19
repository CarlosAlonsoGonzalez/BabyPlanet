package com.example.demo.hijo;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

public class HijoServiceImpl implements HijoService {

    @Autowired
    private HijoRepo hijoRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public HijoDto save(@Valid HijoDto hijo) {
        Hijo hijoEntity = modelMapper.map(hijo, Hijo.class);
        hijoEntity = hijoRepo.save(hijoEntity);
        return modelMapper.map(hijoEntity, HijoDto.class);
    }

    @Override
    public HijoDto get(Long id) {
        Hijo hijoEntity = hijoRepo.getReferenceById(id);
        HijoDto hijolDto = modelMapper.map(hijoEntity, HijoDto.class);
        return hijolDto;
    }

    @Override
    public List<HijoDto> getAll() {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public List<HijoDto> getMany(List<Long> ids) {
        List<Hijo> listaHijoEntities = hijoRepo.findManyById(ids);
        List<HijoDto> listaHijoDtos = listaHijoEntities.stream().map(hijo -> modelMapper.map(hijo, HijoDto.class))
        .collect(Collectors.toList());
        return listaHijoDtos;
    }

    @Override
    public void delete(Long id) {
        
        hijoRepo.deleteById(id);  
     }

}
