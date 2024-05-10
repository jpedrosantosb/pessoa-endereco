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

import com.jpbastos.gerenciadorPessoas.model.entities.Pessoa;
import com.jpbastos.gerenciadorPessoas.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@GetMapping
	public ResponseEntity<List<Pessoa>> listarTodasPessoas() {
		List<Pessoa> pessoas = service.findAll();
		return ResponseEntity.ok().body(pessoas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
		Pessoa pessoa = service.findById(id);
		return ResponseEntity.ok().body(pessoa);
	}

	@PostMapping
	public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
		Pessoa pessoaCriada = service.criarPessoa(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoaCriada.getId())
				.toUri();
		return ResponseEntity.created(uri).body(pessoaCriada);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
		Pessoa pessoaSalva = service.update(id, pessoaAtualizada);
		return ResponseEntity.ok().body(pessoaSalva);
	}

}
