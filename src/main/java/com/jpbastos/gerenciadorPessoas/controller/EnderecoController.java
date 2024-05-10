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

import com.jpbastos.gerenciadorPessoas.model.entities.Endereco;
import com.jpbastos.gerenciadorPessoas.model.entities.Pessoa;
import com.jpbastos.gerenciadorPessoas.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@GetMapping
	public ResponseEntity<List<Endereco>> listarTodosEnderecos() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long id) {
		Endereco endereco = service.findById(id);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(endereco);
	}

	@PostMapping
	public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
		Endereco enderecoCriado = service.insert(endereco);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(enderecoCriado.getId())
				.toUri();
		return ResponseEntity.created(uri).body(enderecoCriado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @RequestBody Endereco enderecoAtualizado) {
		Endereco enderecoSalvo = service.update(id, enderecoAtualizado);
		return ResponseEntity.ok().body(enderecoSalvo);
	}
}
