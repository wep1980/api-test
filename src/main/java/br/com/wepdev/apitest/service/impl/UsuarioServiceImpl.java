package br.com.wepdev.apitest.service.impl;

import br.com.wepdev.apitest.model.Usuario;
import br.com.wepdev.apitest.repositories.UsuarioRepository;
import br.com.wepdev.apitest.service.UsuarioService;
import br.com.wepdev.apitest.service.exceptions.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario findById(Long id) {
        Optional<Usuario> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado"));
    }
}
