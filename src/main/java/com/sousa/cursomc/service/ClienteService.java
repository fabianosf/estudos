package com.sousa.cursomc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sousa.cursomc.domain.Cliente;
import com.sousa.cursomc.exception.ObjectNotFoundException;
import com.sousa.cursomc.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		Cliente obj = clienteRepository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto nao encontrado ! ID:"+ id 
					+ "Tipo:" +Cliente.class.getName());
		}
		return obj;
	}
	
	

}
