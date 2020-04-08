package com.elecao.teste.java.teste.java.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.elecao.teste.java.teste.java.Entidades.Candidato;

public interface CandidatoRepository extends CrudRepository<Candidato, Long>{
	List<Candidato> findByCargo_id(Long cargo_id);
}
