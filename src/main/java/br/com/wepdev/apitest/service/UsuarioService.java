package br.com.wepdev.apitest.service;

import br.com.wepdev.apitest.model.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario findById(Long id);

    List<Usuario> findAll();
}
