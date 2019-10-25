package com.sousa.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sousa.cursomc.domain.Categoria;
import com.sousa.cursomc.domain.Cidade;
import com.sousa.cursomc.domain.Cliente;
import com.sousa.cursomc.domain.Endereco;
import com.sousa.cursomc.domain.Estado;
import com.sousa.cursomc.domain.Produto;
import com.sousa.cursomc.domain.enums.TipoCliente;
import com.sousa.cursomc.repository.CategoriaRepository;
import com.sousa.cursomc.repository.CidadeRepository;
import com.sousa.cursomc.repository.ClienteRepository;
import com.sousa.cursomc.repository.EnderecoRepository;
import com.sousa.cursomc.repository.EstadoRepository;
import com.sousa.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	 

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//ManyToMany
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		//categoria tem uma relacao com produtos
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//produto tem uma relacao com categorias
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));	
		
		
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		
		//OneToMany e ManyToOne.... 
		//Cidade ja consegue pegar estado pq tem um construtor que ajuda la
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Sao Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		//Quem sao as cidades associadas com o estado ? 
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		//criei o cliente e ja adicionei o telefone a esse cliente.
		Cliente cli1 = new Cliente(null, "Maria Silva","maria@gmail.com","05798769332",TipoCliente.PESSOAFISICA );
		cli1.getTelefones().addAll(Arrays.asList("22645587","99666632"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300","Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua das Pedras", "500","Apto 501", "Centro", "38220834", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
		
	}

}
