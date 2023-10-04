package br.com.belval.crud.crontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.belval.crud.model.Produto;

@Controller
public class ProdutoController {
	
	private static List<Produto> lista = new ArrayList<>();
	private static int proxId = 1;

	@GetMapping("/produto/novo")
	public String novo(Model model) {
		model.addAttribute("produto", new Produto());
		return "produto";
	}
	
	@GetMapping("/produto/{id}/edit")
	public String editar(@PathVariable int id, Model model) {
		
		Produto produto = buscarPorId(id);
		
		if (produto == null) {
			return "produto-nao-encontrado";
		}
		
		model.addAttribute("produto", produto);
		
		return "produto";
	}
	
	@PostMapping("/produto/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("novo-produto-criado");
		
		if (produto.getId() == 0) {
			insert(produto);
		} else {
			update(produto);
		}
		
		modelAndView.addObject("novoProduto", produto);
		return modelAndView;
	}

	private void insert(Produto produto) {
		produto.setId(proxId++);
		lista.add(produto);
	}

	private void update(Produto produto) {
		ListIterator<Produto> it = lista.listIterator();
		while(it.hasNext()) {
			Produto encontrado = it.next();
			if (encontrado.getId() == produto.getId()) {
				it.remove();
				it.add(produto);
			}
		}
	}

	@GetMapping("/produto/list")
	public String list(Model model) {
		model.addAttribute("produtos", lista);
		return "lista-produtos";
	}
	
	@GetMapping("/produto/{id}")
	public String detalhe(@PathVariable int id, Model model) {
		Produto produto = buscarPorId(id);
		
		if (produto != null) {
			model.addAttribute("novoProduto", produto);
			return "novo-produto-criado";
		}
		
		return "produto-nao-encontrado";
	}

	private Produto buscarPorId(int id) {
		Produto encontrou = null;
		for(Produto p : lista) {
			if (p.getId() == id) {
				//encontrou o produto solicitado
				encontrou = p;
				break;
			}
		}
		return encontrou;
	}
	
	

	
}
