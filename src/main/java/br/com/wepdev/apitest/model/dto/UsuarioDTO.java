package br.com.wepdev.apitest.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;

    //@JsonIgnore // Esse campo nao sera retornado na representação, mas dessa forma na hora de salvar um novo usuario, a senha fica null
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Libera o acesso para escrita(Salva ou atualiza), mas para leitura não(Não é vizualizado na representação(POSTMAN))
    private String senha;
}
