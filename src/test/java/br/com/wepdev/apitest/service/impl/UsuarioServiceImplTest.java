package br.com.wepdev.apitest.service.impl;

import br.com.wepdev.apitest.model.Usuario;
import br.com.wepdev.apitest.model.dto.UsuarioDTO;
import br.com.wepdev.apitest.repositories.UsuarioRepository;
import br.com.wepdev.apitest.service.exceptions.ObjetoNaoEncontradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioServiceImplTest {

    public static final Long   ID     = 1L;
    public static final String NOME   = "teste";
    public static final String EMAIL  = "usuario@gmail.com";
    public static final String SENHA  = "123";

    @InjectMocks // Criando uma instancia do usuarioServiceImpl
    private UsuarioServiceImpl service;

    @Mock // Anotacão para criar um Mock do repository
    private UsuarioRepository repository;

    @Mock // Anotacão para criar um Mock do ModelMapper
    private ModelMapper mapper;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;
    private Optional<Usuario> optionalUsuario;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inciando os Mocks dessa classe
        iniciarUsuario(); // Iniciando o metodo que cria os usuarios
    }

    @Test
    void deveriaTrazerUmUsuarioPorId() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalUsuario); // Mocando a resposta do repository
        Usuario response = service.findById(ID); // Fazendo a chamada do metodo no service

        Assertions.assertNotNull(response); // Assegura que o response nao esta nulo

        // espera que na resposta seja um classe do tipo Usuario.class, e compara com o response que é do tipo Usuario.class
        Assertions.assertEquals(Usuario.class, response.getClass()); // PASSA NO TESTE
        //Assertions.assertEquals(UsuarioDTO.class, response.getClass()); //  NÃO PASSA NO TESTE

        Assertions.assertEquals(ID, response.getId()); // Assegura que o ID passado e o mesmo Id do response
        Assertions.assertEquals(NOME, response.getNome()); // Assegura que o nome passado e o mesmo nome do response
        Assertions.assertEquals(EMAIL, response.getEmail()); // Assegura que o email passado e o mesmo email do response
        Assertions.assertEquals(SENHA, response.getSenha()); // Assegura que a senha passado e a mesmo senha do response
    }

    @Test
    void deveriaAcontecerUmaExceptionQuandoUsuarioNaoForEncontrado(){
        Mockito.when(repository.findById(Mockito.anyLong()))
                .thenThrow(new ObjetoNaoEncontradoException("Objeto não encontrado")); // Quando chamar o findById lança a exception de ObjetoNaoEncontrado
        try {
            service.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjetoNaoEncontradoException.class, ex.getClass()); // Assegura que a exception e da mesma classe
            assertEquals("Objeto não encontrado", ex.getMessage()); // Assegura que a mensagem de exception é a mesma passada no mock
        }
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
        optionalUsuario = Optional.of(new Usuario(ID, NOME, EMAIL, SENHA));
    }
}