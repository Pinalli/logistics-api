package com.pinalli.logisticsapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.pinalli.logisticsapi.domain.exception.NegocioException;
import com.pinalli.logisticsapi.domain.model.Entrega;
import com.pinalli.logisticsapi.domain.model.Ocorrencia;
import com.pinalli.logisticsapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
		
	private BuscaEntregaService buscaEntregaService;
		
	@Transactional
		public Ocorrencia registrar(Long entregaId, String descricao) {
		
			Entrega entrega = buscaEntregaService.buscar(entregaId);
			
			return entrega.adicionarOcorrencia(descricao);
		}
}
