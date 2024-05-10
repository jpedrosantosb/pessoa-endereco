package com.jpbastos.gerenciadorPessoas.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeCompleto;
	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(dataNascimento, enderecos, id, nomeCompleto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(enderecos, other.enderecos)
				&& Objects.equals(id, other.id) && Objects.equals(nomeCompleto, other.nomeCompleto);
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nomeCompleto=" + nomeCompleto + ", dataNascimento=" + dataNascimento
				+ ", enderecos=" + enderecos + "]";
	}

}
