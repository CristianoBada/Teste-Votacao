package com.elecao.teste.java.teste.java.Entidades;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Eleicao extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	// Nome da eleição
	@NotEmpty
	private String nome;

	// Data de inicio da eleição
	@NotEmpty
	@Size(min = 10, max = 10)
	private String inicio;

	// Data final da eleição
	@NotEmpty
	@Size(min = 10, max = 10)
	private String fim;

	@OneToMany
	private Set<Cargo> cargos;

	@OneToMany
	private Set<Eleitor> eleitores;

	public Set<Eleitor> getEleitores() {
		return eleitores;
	}

	public void setEleitores(Set<Eleitor> eleitores) {
		this.eleitores = eleitores;
	}

	public Set<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(Set<Cargo> cargos) {
		this.cargos = cargos;
	}

	public Eleicao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}

}
