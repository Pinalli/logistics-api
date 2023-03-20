package com.pinalli.logisticsapi.domain.service;

import org.springframework.stereotype.Service;

import com.pinalli.logisticsapi.domain.exception.NegocioException;
import com.pinalli.logisticsapi.domain.model.Entrega;
import com.pinalli.logisticsapi.domain.model.StatusEntrega;
import com.pinalli.logisticsapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {
	
	private EntregaRepository entregaRepository;
	private BuscaEntregaService buscaEntregaService;
	
	public void finalizar(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);		
		
		entrega.finalizar();
		
		entregaRepository.save(entrega);
	}
}
