package br.com.wepdev.apitest.service;

import br.com.wepdev.apitest.model.Usuario;
import br.com.wepdev.apitest.model.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    Usuario findById(Long id);

    List<Usuario> findAll();

    Usuario create(UsuarioDTO obj);

    Usuario update(UsuarioDTO obj);
}
