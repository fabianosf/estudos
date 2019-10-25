package com.sousa.cursomc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sousa.cursomc.domain.Categoria;
import com.sousa.cursomc.exception.ObjectNotFoundException;
import com.sousa.cursomc.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}	
	
	public Categoria find(Integer id) {
		Categoria obj = categoriaRepository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto nao encontrado ! ID:"+ id 
					+ "Tipo:" +Categoria.class.getName());
		}
		return obj;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return categoriaRepository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		categoriaRepository.delete(id);
		}
		catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Nao é possivel excluir uma categoria que possui produtos");
		}
	}
	
	
	
	
	
	
	
	
	

}
