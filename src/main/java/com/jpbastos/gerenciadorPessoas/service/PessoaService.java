package com.jpbastos.gerenciadorPessoas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jpbastos.gerenciadorPessoas.model.entities.Pessoa;
import com.jpbastos.gerenciadorPessoas.repositories.PessoaRepository;
import com.jpbastos.gerenciadorPessoas.service.exceptions.DatabaseException;
import com.jpbastos.gerenciadorPessoas.service.exceptions.ResourceNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	public Pessoa findById(Long id) {
		Optional<Pessoa> obj = pessoaRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Pessoa criarPessoa(Pessoa obj) {
		return pessoaRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			pessoaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Pessoa update(Long id, Pessoa obj) {
		Pessoa entity = pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(entity, obj);
		return pessoaRepository.save(entity);
	}

	private void updateData(Pessoa entity, Pessoa obj) {
		entity.setNomeCompleto(obj.getNomeCompleto());
		entity.setDataNascimento(obj.getDataNascimento());
		entity.setEnderecos(obj.getEnderecos());
	}

}
