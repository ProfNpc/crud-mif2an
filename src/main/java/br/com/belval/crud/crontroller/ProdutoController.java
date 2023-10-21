package br.com.belval.crud.crontroller;

import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.belval.crud.model.Produto;
import br.com.belval.crud.repository.ProdutoRepository;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;

	@GetMapping("/produto/novo")
	public String novo(Model model) {
		model.addAttribute("produto", new Produto());
		return "produto";
	}
	
	@GetMapping("/produto/{id}/edit")
	public String editar(@PathVariable int id, Model model) {
		
		Produto produto = repository.findById(id);
		
		if (produto == null) {
			return "produto-nao-encontrado";
		}
		
		model.addAttribute("produto", produto);
		
		return "produto";
	}
	
	@PostMapping("/produto/novo")
	public ModelAndView novo(Produto produto, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/produto/list");
		
		if (produto.getId() == 0) {
			redirectAttributes.addFlashAttribute("msg","Novo produto criado!");
		} else {
			redirectAttributes.addFlashAttribute("msg","Produto atualizado!");
		}
		repository.save(produto);
		
		return modelAndView;
	}

	@GetMapping("/produto/list")
	public String list(Model model) {
		model.addAttribute("produtos", repository.findAll());
		return "lista-produtos";
	}
	
	@GetMapping("/produto/{id}")
	public String detalhe(@PathVariable int id, Model model) {
		Produto produto = repository.findById(id);
		
		if (produto != null) {
			model.addAttribute("novoProduto", produto);
			return "novo-produto-criado";
		}
		
		return "produto-nao-encontrado";
	}

	@GetMapping("/produto/{id}/excluir")
	public String excluir(@PathVariable int id, RedirectAttributes redirectAttributes) {
		repository.deleteById(id);
		
		redirectAttributes.addFlashAttribute("msg","Produto excluído!");
		
		return "redirect:/produto/list";
	}
}
