package com.jpbastos.gerenciadorPessoas;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jpbastos.gerenciadorPessoas.entities.Endereco;
import com.jpbastos.gerenciadorPessoas.entities.Pessoa;
import com.jpbastos.gerenciadorPessoas.repositories.EnderecoRepository;
import com.jpbastos.gerenciadorPessoas.repositories.PessoaRepository;

@SpringBootApplication
public class GerenciadorPessoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPessoasApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(PessoaRepository pessoaRep, EnderecoRepository enderecoRep) {
		return args -> {
			pessoaRep.deleteAll();
			enderecoRep.deleteAll();

			Pessoa pessoa1 = new Pessoa();
			pessoa1.setNomeCompleto("João Pedro dos Santos");
			pessoa1.setDataNascimento(LocalDate.now());
			Pessoa pessoa2 = new Pessoa();
			pessoa2.setNomeCompleto("Kamila Braga Ortiz");
			pessoa2.setDataNascimento(LocalDate.of(1996, 5, 20));

			Endereco endereco1 = new Endereco(null, "Rua das Flores", "12345-678", "123", "São Paulo", "SP", pessoa1,
					true);
			Endereco endereco2 = new Endereco(null, "Rua das Arvores", "12345-321", "0102", "Brasília", "DF", pessoa1,
					false);
			Endereco endereco3 = new Endereco(null, "Rua das Plantas", "12345-100", "020", "Rio de Janeiro", "RJ",
					pessoa2, true);

			pessoaRep.saveAll(Arrays.asList(pessoa1, pessoa2));
			enderecoRep.saveAll(Arrays.asList(endereco1, endereco2, endereco3));

			pessoa1.getEnderecos().add(endereco1);
			pessoa1.getEnderecos().add(endereco2);
			pessoa2.getEnderecos().add(endereco3);

			pessoaRep.saveAll(Arrays.asList(pessoa1, pessoa2));
			//enderecoRep.saveAll(Arrays.asList(endereco1, endereco2, endereco3));

		};
	}

}
