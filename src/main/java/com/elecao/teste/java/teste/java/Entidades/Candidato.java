package com.elecao.teste.java.teste.java.Entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

@Entity
public class Candidato extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String nome;

	@Lob
	private byte[] imagem;

	@NonNull
	private Integer votos;

	@NotNull
	@ManyToOne
	@JoinColumn(name="cargo_id")
	private Cargo cargo;
	
	public Candidato() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Integer getVotos() {
		return votos;
	}

	public void setVotos(Integer votos) {
		this.votos = votos;
	}

}