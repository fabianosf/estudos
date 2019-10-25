package com.sousa.cursomc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sousa.cursomc.domain.Categoria;
import com.sousa.cursomc.exception.ObjectNotFoundException;
import com.sousa.cursomc.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) {
		Categoria obj = categoriaRepository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto nao encontrado ! ID:"+ id 
					+ "Tipo:" +Categoria.class.getName());
		}
		return obj;
	}
	
	

}
