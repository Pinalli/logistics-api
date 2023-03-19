package com.pinalli.logisticsapi.domain.service;

import org.springframework.stereotype.Service;

import com.pinalli.logisticsapi.domain.exception.EntidadeNaoEncontradaException;

import com.pinalli.logisticsapi.domain.model.Entrega;
import com.pinalli.logisticsapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada!"));
	}
}
