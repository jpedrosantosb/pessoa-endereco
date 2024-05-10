package com.jpbastos.gerenciadorPessoas.model.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO implements Serializable{

	private Long id;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String cep;
	
}
