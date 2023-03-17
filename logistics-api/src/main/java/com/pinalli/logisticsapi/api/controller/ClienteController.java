package com.pinalli.logisticsapi.api.controller;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pinalli.logisticsapi.domain.model.Cliente;
import com.pinalli.logisticsapi.domain.repository.ClienteRepository;
import com.pinalli.logisticsapi.domain.service.CatalagoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	

	private ClienteRepository clienteRepository;
	private CatalagoClienteService catalagoClienteService;
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();		
	}
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {	
		
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(cliente))//ou .map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		/*
		 * Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		 * 
		 * if(cliente.isPresent()) { return ResponseEntity.ok(cliente.get()); } return
		 * ResponseEntity.notFound().build();
		 */
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return catalagoClienteService.salvar(cliente);
		//return clienteRepository.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente>atualizar(@PathVariable Long clienteId,
			@Valid @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);		
		cliente = catalagoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		catalagoClienteService.excluir(clienteId);	
		
		return ResponseEntity.noContent().build();
	}
}
