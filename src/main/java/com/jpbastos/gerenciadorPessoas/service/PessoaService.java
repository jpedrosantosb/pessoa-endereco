package com.jpbastos.gerenciadorPessoas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jpbastos.gerenciadorPessoas.model.dtos.PessoaDTO;
import com.jpbastos.gerenciadorPessoas.model.entities.Pessoa;
import com.jpbastos.gerenciadorPessoas.model.mapper.PessoaMapper;
import com.jpbastos.gerenciadorPessoas.repositories.PessoaRepository;
import com.jpbastos.gerenciadorPessoas.service.exceptions.DatabaseException;
import com.jpbastos.gerenciadorPessoas.service.exceptions.ResourceNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	private final PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;

	public List<Pessoa> buscarTodos() {
		return pessoaRepository.findAll();
	}

	public Pessoa buscarPorId(Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		return pessoa.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Pessoa criarPessoa(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	public void deletarPorId(Long id) {
		try {
			pessoaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public void atualizarPessoa(Long idPessoa, PessoaDTO pessoaDTO) {
		if (pessoaDTO == null) {
			throw new DatabaseException("PessoaDTO não pode ser nulo");
		}
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(idPessoa);
		if (pessoaOptional.isEmpty()) {
			throw new ResourceNotFoundException(idPessoa);
		}
		Pessoa pessoa = pessoaMapper.toObject(pessoaDTO);
		pessoaRepository.save(pessoa);
	}
	
//	public Pessoa atualizarPessoa(Long id, PessoaDTO obj) {
//		Pessoa entity = pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
//		updateData(entity, obj);
//		return pessoaRepository.save(entity);
//	}
//
//	private void updateData(Pessoa entity, PessoaDTO obj) {
//		entity.setNomeCompleto(obj.getNomeCompleto());
//		entity.setDataNascimento(obj.getDataNascimento());
//		entity.setEnderecos(obj.getEnderecos());
//	}

}
