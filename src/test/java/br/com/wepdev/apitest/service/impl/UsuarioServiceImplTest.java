package br.com.wepdev.apitest.service.impl;

import br.com.wepdev.apitest.model.Usuario;
import br.com.wepdev.apitest.model.dto.UsuarioDTO;
import br.com.wepdev.apitest.repositories.UsuarioRepository;
import br.com.wepdev.apitest.service.exceptions.DataIntegratyViolationException;
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

import java.util.List;
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

    private Usuario usuarioUm;
    private Usuario usuarioDois;
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
    void deveriaBuscarUmaListaDeUsuarios() {
        Mockito.when(repository.findAll()).thenReturn(List.of(usuarioUm)); // Retorna uma lista de 2 usuarios

        List<Usuario> response = service.findAll(); // chamando o metodo do service

        assertNotNull(response); // Verifica se a resposta não e nula
        assertEquals(1, response.size()); // verificando se o tamanho da lista e de 2 elementos
        assertEquals(Usuario.class, response.get(0).getClass()); // Verifica se o objeto da posição 0 e do tipo Usario

        assertEquals(ID, response.get(0).getId()); // verifica se o elemento da posição 0 do response tem o mesmo ID do elemento passado na lista
        Assertions.assertEquals(NOME, response.get(0).getNome()); // Assegura que o nome passado e o mesmo nome do response que esta na posição 0
        Assertions.assertEquals(EMAIL, response.get(0).getEmail()); // Assegura que o email passado e o mesmo email do response que esta na posição 0
        Assertions.assertEquals(SENHA, response.get(0).getSenha()); // Assegura que a senha passado e a mesmo senha do response que esta na posição 0
    }

    @Test
    void deveriaCriarUmUsuario() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(usuarioUm); // Resposta mockada

        Usuario response = service.create(usuarioDTO); // Chamando o metodo create do service

        assertNotNull(response); // Verifica se a resposta não e nula
        assertEquals(Usuario.class, response.getClass()); // Verifica se o objeto e do tipo Usario
        Assertions.assertEquals(ID, response.getId()); // Assegura que o ID passado e o mesmo Id do response
        Assertions.assertEquals(NOME, response.getNome()); // Assegura que o nome passado e o mesmo nome do response
        Assertions.assertEquals(EMAIL, response.getEmail()); // Assegura que o email passado e o mesmo email do response
        Assertions.assertEquals(SENHA, response.getSenha()); // Assegura que a senha passado e a mesmo senha do response

    }

    @Test
    void deveriaLancarUmaDataIntegrityViolationExceptionAoCriarUmUsuario() {
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUsuario); // Resposta mockada

        try {
            optionalUsuario.get().setId(2L); // Alterando o ID para acontecer o erro, pois se nao for trocado a lógica do metedo entende que esta sendo feita um update
            service.create(usuarioDTO);
        }catch (Exception ex){
           assertEquals(DataIntegratyViolationException.class, ex.getClass()); // Assegura que a exception lançada e da mesma classe
           assertEquals("Email ja cadastrado no sistema", ex.getMessage()); // Assegura que a mensagem de exception é a mesma passada no mock
        }
    }

    @Test
    void deveriaFazerUpdateDeUmUsuario() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(usuarioUm); // Resposta mockada

        Usuario response = service.update(usuarioDTO); // Chamando o metodo create do service

        assertNotNull(response); // Verifica se a resposta não e nula
        assertEquals(Usuario.class, response.getClass()); // Verifica se o objeto e do tipo Usario
        Assertions.assertEquals(ID, response.getId()); // Assegura que o ID passado e o mesmo Id do response
        Assertions.assertEquals(NOME, response.getNome()); // Assegura que o nome passado e o mesmo nome do response
        Assertions.assertEquals(EMAIL, response.getEmail()); // Assegura que o email passado e o mesmo email do response
        Assertions.assertEquals(SENHA, response.getSenha()); // Assegura que a senha passado e a mesmo senha do response
    }


    @Test
    void deveriaLancarUmaDataIntegrityViolationExceptionAoFazerUpdateDeUmUsuario() {
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUsuario); // Resposta mockada

        try {
            optionalUsuario.get().setId(2L); // Alterando o ID para acontecer o erro, pois se nao for trocado a lógica do metedo entende que esta sendo feita um update
            service.update(usuarioDTO);
        }catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass()); // Assegura que a exception lançada e da mesma classe
            assertEquals("Email ja cadastrado no sistema", ex.getMessage()); // Assegura que a mensagem de exception é a mesma passada no mock
        }
    }

    @Test
    void deveriaDeletarUmUsuario() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalUsuario);
        Mockito.doNothing().when(repository).deleteById(Mockito.anyLong()); // Nao faça nada quando o deleteById for chamado passando um valor Long

        service.delete(ID);
        Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    private void iniciarUsuario(){
        usuarioUm = new Usuario(ID, NOME, EMAIL, SENHA);
        usuarioDois = new Usuario(ID, NOME, EMAIL, SENHA);
        usuarioDTO = new UsuarioDTO(ID, NOME, EMAIL, SENHA);
        optionalUsuario = Optional.of(new Usuario(ID, NOME, EMAIL, SENHA));
    }
}