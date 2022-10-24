package com.usuario.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entity.Usuario;
import com.usuario.service.feign.CarroFeignClient;
import com.usuario.service.feign.MotoFeignClient;
import com.usuario.service.model.Carro;
import com.usuario.service.model.Moto;
import com.usuario.service.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CarroFeignClient carroFeignClient;
	
	@Autowired
	private MotoFeignClient motoFeignClient;
	
	public List<Carro> getCarros(int usuarioId) {
		List<Carro> carros = restTemplate.getForObject(
				"http://localhost:8002/carros/usuarios/" + usuarioId, List.class);
		return carros;
	}
	
	public List<Moto> getMotos(int usuarioId) {
		List<Moto> motos = restTemplate.getForObject(
				"http://localhost:8003/motos/usuarios/" + usuarioId, List.class);
		return motos;
	}
	
	public Carro saveCarro(int usuarioId, Carro carro) {
		carro.setUsuarioId(usuarioId);
		Carro nuevoCarro = carroFeignClient.save(carro);
		return nuevoCarro;
	}
	
	public Moto saveMoto(int usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto nuevaMoto = motoFeignClient.save(moto);
		return nuevaMoto;
	}
	
	public Map<String, Object> getUsuarioAndVehiculos(int usuarioId) {
		Map<String, Object> resultado = new HashMap<>();
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
		
		if (usuario == null) {
			resultado.put("Mensaje", "Usuario no Existe");
			return resultado;
		}
		
		resultado.put("Usuario", usuario);
		List<Carro> carros = carroFeignClient.getCarros(usuarioId);
		if (carros == null) {
			resultado.put("Carros", "El usuario no tiene carros");
		}
		else {
			resultado.put("Carros", carros);
		}
		
		List<Moto> motos = motoFeignClient.getMotos(usuarioId);
		if (motos == null) {
			resultado.put("Motos", "El usuario no tiene motos");
		}
		else {
			resultado.put("Motos", motos);
		}
		
		return resultado;
	}
	
	public List<Usuario> getAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return nuevoUsuario;
	}
	
}
