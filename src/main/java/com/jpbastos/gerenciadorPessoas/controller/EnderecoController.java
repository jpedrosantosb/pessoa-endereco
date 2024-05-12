package com.jpbastos.gerenciadorPessoas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jpbastos.gerenciadorPessoas.model.dtos.EnderecoDTO;
import com.jpbastos.gerenciadorPessoas.model.entities.Endereco;
import com.jpbastos.gerenciadorPessoas.service.EnderecoService;
import com.jpbastos.gerenciadorPessoas.service.exceptions.DatabaseException;
import com.jpbastos.gerenciadorPessoas.service.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@GetMapping
	public ResponseEntity<List<Endereco>> listarTodosEnderecos() {
		return ResponseEntity.ok().body(service.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long id) {
		Endereco endereco = service.buscarPorId(id);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(endereco);
	}

	@PostMapping
	public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
		Endereco enderecoCriado = service.criarEndereco(endereco);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(enderecoCriado.getId())
				.toUri();
		return ResponseEntity.created(uri).body(enderecoCriado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
		service.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
		try {
			service.atualizarEndereco(id, enderecoDTO);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (DatabaseException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
