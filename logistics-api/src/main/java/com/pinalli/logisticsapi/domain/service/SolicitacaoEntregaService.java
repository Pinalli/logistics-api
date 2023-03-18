package com.pinalli.logisticsapi.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinalli.logisticsapi.domain.exception.NegocioException;
import com.pinalli.logisticsapi.domain.model.Cliente;
import com.pinalli.logisticsapi.domain.model.Entrega;
import com.pinalli.logisticsapi.domain.model.StatusEntrega;
import com.pinalli.logisticsapi.domain.repository.ClienteRepository;
import com.pinalli.logisticsapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
	
	private CatalagoClienteService catalagoClienteService;
	private EntregaRepository entregaRepository;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		
		Cliente cliente = catalagoClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		
		return entregaRepository.save(entrega);
		
	}

}
