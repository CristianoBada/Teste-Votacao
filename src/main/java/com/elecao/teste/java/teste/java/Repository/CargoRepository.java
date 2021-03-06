package com.elecao.teste.java.teste.java.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elecao.teste.java.teste.java.Entidades.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{
	List<Cargo> findByEleicao_id(Long eleicao_id);
}
