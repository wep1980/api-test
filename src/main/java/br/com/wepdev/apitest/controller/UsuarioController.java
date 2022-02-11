package br.com.wepdev.apitest.controller;

import br.com.wepdev.apitest.model.Usuario;
import br.com.wepdev.apitest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {


    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
