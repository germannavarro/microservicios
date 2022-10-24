package com.usuario.service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.model.Carro;

@FeignClient(name = "carro-service", url = "http://localhost:8002", path = "/carros")
public interface CarroFeignClient {

	@PostMapping
	public Carro save(@RequestBody Carro carro);

	@GetMapping("/usuarios/{usuarioId}")
	public List<Carro> getCarros(@PathVariable("usuarioId") int usuarioId);
	
}
