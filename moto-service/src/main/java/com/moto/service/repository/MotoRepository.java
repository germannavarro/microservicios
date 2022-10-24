package com.moto.service.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moto.service.entity.Moto;

@Repository
public interface MotoRepository extends CrudRepository<Moto, Integer> {

	List<Moto> findByUsuarioId(int usuarioId);

}
