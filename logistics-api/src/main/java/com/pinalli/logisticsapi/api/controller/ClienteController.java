package com.pinalli.logisticsapi.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinalli.logisticsapi.domain.model.Cliente;



@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		
		var c1 = new Cliente(); //Cliente ou var
		c1.setId(1L);
		c1.setNome("João Carlos");
		c1.setTelefone("3322-4867");
		c1.setEmail("joao@email.com");
		
		var c2 = new Cliente();
		c2.setId(2L);
		c2.setNome("Maria");
		c2.setTelefone("3322-6745");
		c2.setEmail("maria@email.com");
		
		
		return Arrays.asList(c1,c2);
	}
}
