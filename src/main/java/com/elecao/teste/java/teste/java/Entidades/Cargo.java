package com.elecao.teste.java.teste.java.Entidades;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Cargo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String nome;

	@NotNull
	@ManyToOne
	@JoinColumn(name="eleicao_id")
	private Eleicao eleicao;
	
	@OneToMany
	private Set<Candidato> candidatos;

	public Cargo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Eleicao getEleicao() {
		return eleicao;
	}

	public void setEleicao(Eleicao eleicao) {
		this.eleicao = eleicao;
	}
}
