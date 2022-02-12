package br.com.wepdev.apitest.controller;

import br.com.wepdev.apitest.model.Usuario;
import br.com.wepdev.apitest.model.dto.UsuarioDTO;
import br.com.wepdev.apitest.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

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
    void deveriaBuscarUmUsuarioPorId() {
        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(usuario);
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = controller.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody()); // Assegura que o corpo da resposta nao esta nulo
        Assertions.assertEquals(ResponseEntity.class, response.getClass()); // Assegura que o corpo da resposta seja igual ao do response, que é ResponseEntity
        Assertions.assertEquals(UsuarioDTO.class, response.getBody().getClass()); // Assegura que o tipo do response e da classe UsuarioDTO

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NOME, response.getBody().getNome());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(SENHA, response.getBody().getSenha());
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