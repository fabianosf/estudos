package com.sousa.cursomc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sousa.cursomc.domain.Pedido;
import com.sousa.cursomc.exception.ObjectNotFoundException;
import com.sousa.cursomc.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscar(Integer id) {
		Pedido obj = pedidoRepository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto nao encontrado ! ID:"+ id 
					+ "Tipo:" +Pedido.class.getName());
		}
		return obj;
	}
	
	

}
