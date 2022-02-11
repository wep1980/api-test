package br.com.wepdev.apitest.controller;

import br.com.wepdev.apitest.model.dto.UsuarioDTO;
import br.com.wepdev.apitest.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UsuarioService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UsuarioDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
       return ResponseEntity.ok()
               .body(service.findAll().stream().map(x -> mapper.map(x, UsuarioDTO.class)).collect(Collectors.toList()));
    }
}
