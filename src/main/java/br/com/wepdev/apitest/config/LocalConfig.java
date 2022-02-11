package br.com.wepdev.apitest.config;

import br.com.wepdev.apitest.model.Usuario;
import br.com.wepdev.apitest.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UsuarioRepository repository;

    /**
     * Start o banco sempre que esse perfil estiver ativo
     */
    @Bean
    public void startDB(){
        Usuario usuario1 = new Usuario(null, "Waldir", "wep@gmail.com", "123");
        Usuario usuario2 = new Usuario(null, "Luiz", "luiz@gmail.com", "123");

        repository.saveAll(List.of(usuario1, usuario2)); // Salvando uma lista de usuario
    }
}
