package com.elecao.teste.java.teste.java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elecao.teste.java.teste.java.Entidades.Eleicao;
import com.elecao.teste.java.teste.java.Entidades.Eleitor;

import java.util.List;

public interface EleicaoRepository extends JpaRepository<Eleicao, Long>{
	
	List<Eleicao> findByFimGreaterThanEqualAndInicioLessThanEqual(String dataAtual1, String dataAtual2);
	
	List<Eleicao> findByFimLessThanEqual(String dataAtual);
}
