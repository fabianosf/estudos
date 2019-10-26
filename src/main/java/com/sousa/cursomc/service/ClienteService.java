package com.sousa.cursomc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sousa.cursomc.domain.Cliente;
import com.sousa.cursomc.dto.ClienteDTO;
import com.sousa.cursomc.exception.ObjectNotFoundException;
import com.sousa.cursomc.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}	
	
	
	
	
	public Cliente find(Integer id) {
		Cliente obj = clienteRepository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto nao encontrado ! ID:"+ id 
					+ "Tipo:" +Cliente.class.getName());
		}
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		clienteRepository.delete(id);
		}
		catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Nao é possivel excluir porque ha entidades relacionadas");
		}
	}
	
	//Paginacao, o numero de paginas que ira mostrar 
	//depois fazer isso na resource
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	//Conversao de Categoria para DTO para metodo insert do resource
		//eh um metodo auxiliar
		//validacao do campo notempty length ...
		public Cliente fromDTO(ClienteDTO objDto) {
			return new Cliente(objDto.getId(), objDto.getNome(),objDto.getEmail(), null, null);
		}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
