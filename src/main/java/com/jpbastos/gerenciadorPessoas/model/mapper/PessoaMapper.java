package com.jpbastos.gerenciadorPessoas.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jpbastos.gerenciadorPessoas.model.dtos.PessoaDTO;
import com.jpbastos.gerenciadorPessoas.model.entities.Pessoa;

@Mapper
public interface PessoaMapper {
	
	PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);
	
	Pessoa toObject(PessoaDTO pessoaDTO);
    PessoaDTO toDTO(Pessoa pessoa);

}
