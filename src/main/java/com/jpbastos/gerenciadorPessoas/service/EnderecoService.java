package com.jpbastos.gerenciadorPessoas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jpbastos.gerenciadorPessoas.model.entities.Endereco;
import com.jpbastos.gerenciadorPessoas.repositories.EnderecoRepository;
import com.jpbastos.gerenciadorPessoas.service.exceptions.DatabaseException;
import com.jpbastos.gerenciadorPessoas.service.exceptions.ResourceNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	public List<Endereco> findAll() {
		return repository.findAll();
	}

	public Endereco findById(Long id) {
		Optional<Endereco> obj = repository.findById(id);
		return obj.get();
	}

	public Endereco insert(Endereco obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Endereco update(Long id, Endereco obj) {
		Endereco entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Endereco entity, Endereco obj) {
		entity.setCep(obj.getCep());
		entity.setCidade(obj.getCidade());
		entity.setEstado(obj.getEstado());
		entity.setLogradouro(obj.getLogradouro());
		entity.setNumero(obj.getNumero());
		entity.setPessoa(obj.getPessoa());
	}
}
