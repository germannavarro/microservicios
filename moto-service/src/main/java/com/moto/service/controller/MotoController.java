package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entity.Moto;
import com.moto.service.service.MotoService;

@RestController
@RequestMapping("/motos")
public class MotoController {

	@Autowired
	private MotoService motoService;
	
	@GetMapping
	public ResponseEntity<List<Moto>> listarMotos() {
		List<Moto> carros = motoService.getAll();
		if (carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carros);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Moto> obtenerMoto(@PathVariable("id") int id) {
		Moto moto = motoService.getMotoById(id);
		if (moto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(moto);
	}
	
	@PostMapping
	public ResponseEntity<Moto> guardarCarro(@RequestBody Moto moto) {
		Moto nuevaMoto = motoService.save(moto);
		return ResponseEntity.ok(nuevaMoto);
	}
	
	@GetMapping("/usuarios/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotosPorUsuarioId(@PathVariable("usuarioId") int id) {
		List<Moto> motos = motoService.byUsuarioId(id);
		return ResponseEntity.ok(motos);
	}
	
	
}
