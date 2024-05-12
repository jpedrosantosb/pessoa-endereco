package com.jpbastos.gerenciadorPessoas.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.jpbastos.gerenciadorPessoas.model.dtos.EnderecoDTO;
import com.jpbastos.gerenciadorPessoas.model.entities.Endereco;

@Mapper
public interface EnderecoMapper {

	EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    EnderecoDTO toDTO(Endereco endereco);
    Endereco toObject(EnderecoDTO enderecoDTO);
	
    void updateFromDTO(EnderecoDTO dto, @MappingTarget Endereco endereco);
}