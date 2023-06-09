package com.pinalli.logisticsapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.pinalli.logisticsapi.domain.exception.NegocioException;
import com.pinalli.logisticsapi.domain.validationgroups.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Entrega {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne	
	private Cliente cliente;	
		
	@Embedded
	private Destinatario destinatario;
	
	private BigDecimal taxa;	
	
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;	
	
	private OffsetDateTime dataPedido;	

	private OffsetDateTime  dataFinalizacao;

	public Ocorrencia adicionarOcorrencia(String descricao) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(this);
		
		this.getOcorrencias().add(ocorrencia);
		
		return ocorrencia;
		
		
	}

	public void finalizar() {
		if(naoPodeSerFinalizada()) {
			throw new NegocioException("A Entrega não pode ser finalizada");
		}
		setStatus(StatusEntrega.FINALIZADA);
		setDataFinalizacao(OffsetDateTime.now());		
	}
		public boolean podeSerFinalizada() {
			return StatusEntrega.PENDENTE.equals(getStatus());
		}
		public boolean naoPodeSerFinalizada() {
			return !podeSerFinalizada();
		}
}
