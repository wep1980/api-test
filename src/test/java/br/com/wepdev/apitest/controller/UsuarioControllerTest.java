package br.com.wepdev.apitest.controller;

import br.com.wepdev.apitest.model.Usuario;
import br.com.wepdev.apitest.model.dto.UsuarioDTO;
import br.com.wepdev.apitest.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioControllerTest {

    public static final Long   ID     = 1L;
    public static final String NOME   = "teste";
    public static final String EMAIL  = "usuario@gmail.com";
    public static final String SENHA  = "123";

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @InjectMocks
    private UsuarioController controller;

    @Mock
    private UsuarioServiceImpl service;

    @Mock
    private ModelMapper mapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarUsuario();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void iniciarUsuario(){
        usuario = new Usuario(ID, NOME, EMAIL, SENHA);
        usuarioDTO = new UsuarioDTO(ID, NOME, EMAIL, SENHA);
    }
}