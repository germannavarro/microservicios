package com.carro.service.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.carro.service.entity.Carro;

@Repository
public interface CarroRepository extends CrudRepository<Carro, Integer> {
	
	List<Carro> findByUsuarioId(int usuarioId);

}
