package com.pinalli.logisticsapi.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinalli.logisticsapi.domain.exception.NegocioException;
import com.pinalli.logisticsapi.domain.model.Cliente;
import com.pinalli.logisticsapi.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalagoClienteService {
	
	private ClienteRepository clienteRepository ;
	
	@Transactional
	public Cliente salvar(Cliente cliente){
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail!");				
			}
			return clienteRepository.save(cliente);		
	}	
		
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);	
	}
}
