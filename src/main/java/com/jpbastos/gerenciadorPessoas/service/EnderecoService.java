package com.jpbastos.gerenciadorPessoas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jpbastos.gerenciadorPessoas.model.dtos.EnderecoDTO;
import com.jpbastos.gerenciadorPessoas.model.entities.Endereco;
import com.jpbastos.gerenciadorPessoas.model.mapper.EnderecoMapper;
import com.jpbastos.gerenciadorPessoas.repositories.EnderecoRepository;
import com.jpbastos.gerenciadorPessoas.service.exceptions.DatabaseException;
import com.jpbastos.gerenciadorPessoas.service.exceptions.ResourceNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

//	@Autowired
//	private PessoaRepository pessoaRepository;

	private final EnderecoMapper enderecoMapper = EnderecoMapper.INSTANCE;

	public List<Endereco> buscarTodos() {
		return repository.findAll();
	}

//	public Pessoa buscarPessoaPorId(Long id) {
//		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
//		return pessoa.orElseThrow(() -> new ResourceNotFoundException(id));
//	}

	public Endereco buscarPorId(Long id) {
		Optional<Endereco> endereco = repository.findById(id);
		return endereco.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Endereco criarEndereco(Endereco endereco) {
		return repository.save(endereco);
	}

	public void deletarPorId(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public void atualizarEndereco(Long idEndereco, EnderecoDTO enderecoDTO) {
		if (enderecoDTO == null) {
			throw new DatabaseException("EnderecoDTO não pode ser nulo");
		}
		Endereco endereco = repository.findById(idEndereco)
				.orElseThrow(() -> new ResourceNotFoundException(idEndereco));
		enderecoMapper.updateFromDTO(enderecoDTO, endereco);
		repository.save(endereco);
	}
}
