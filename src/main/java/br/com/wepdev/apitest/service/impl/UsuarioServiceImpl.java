package br.com.wepdev.apitest.service.impl;

import br.com.wepdev.apitest.model.Usuario;
import br.com.wepdev.apitest.model.dto.UsuarioDTO;
import br.com.wepdev.apitest.repositories.UsuarioRepository;
import br.com.wepdev.apitest.service.UsuarioService;
import br.com.wepdev.apitest.service.exceptions.DataIntegratyViolationException;
import br.com.wepdev.apitest.service.exceptions.ObjetoNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Usuario findById(Long id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado"));
    }

    @Override
    public List<Usuario> findAll(){
        return repository.findAll();
    }

    @Override
    public Usuario create(UsuarioDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Usuario.class));
    }

    @Override
    public Usuario update(UsuarioDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Usuario.class));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    private void findByEmail(UsuarioDTO obj){
        Optional<Usuario> usuario = repository.findByEmail(obj.getEmail());
        if(usuario.isPresent() && !usuario.get().getId().equals(obj.getId())){ // verifica se o id ja cadastrado e diferente do Id que veio por parametro
            throw new DataIntegratyViolationException("Email ja cadastrado no sistema");
        }
    }
}
