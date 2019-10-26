package com.sousa.cursomc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sousa.cursomc.domain.Categoria;
import com.sousa.cursomc.dto.CategoriaDTO;
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
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return categoriaRepository.save(newObj);
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
	
	//Paginacao, o numero de paginas que ira mostrar 
	//depois fazer isso na resource
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	//Conversao de Categoria para DTO para metodo insert do resource
	//eh um metodo auxiliar
	//validacao do campo notempty length ...
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
		 
		
	}
	
	
	
	
	
	
	
	
	

}
