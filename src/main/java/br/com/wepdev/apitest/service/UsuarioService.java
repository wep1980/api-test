package br.com.wepdev.apitest.service;

import br.com.wepdev.apitest.model.Usuario;

public interface UsuarioService {

    Usuario findById(Long id);
}
