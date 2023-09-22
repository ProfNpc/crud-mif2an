package br.com.belval.crud.crontroller;

import java.util.ArrayList;
import java.util.List;

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
	public String novo() {
		return "novo-produto";
	}
	
	@PostMapping("/produto/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("novo-produto-criado");
		
		produto.setId(proxId++);
		
		lista.add(produto);
		
		modelAndView.addObject("novoProduto", produto);
		return modelAndView;
	}

	@GetMapping("/produto/list")
	public String list(Model model) {
		model.addAttribute("produtos", lista);
		return "lista-produtos";
	}
	
	@GetMapping("/produto/{id}")
	public String detalhe(@PathVariable int id, Model model) {
		Produto encontrou = null;
		for(Produto p : lista) {
			if (p.getId() == id) {
				//encontrou o produto solicitado
				encontrou = p;
				break;
			}
		}
		
		if (encontrou != null) {
			model.addAttribute("novoProduto", encontrou);
			return "novo-produto-criado";
		}
		
		return "produto-nao-encontrado";
	}
	
	
	
	
}
