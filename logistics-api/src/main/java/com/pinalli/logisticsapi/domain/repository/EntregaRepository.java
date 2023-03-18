package com.pinalli.logisticsapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pinalli.logisticsapi.domain.model.Entrega;

@Repository 
public interface EntregaRepository extends JpaRepository<Entrega, Long>{

}
