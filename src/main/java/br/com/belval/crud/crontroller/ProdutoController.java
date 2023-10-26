package br.com.belval.crud.crontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.belval.crud.model.Produto;
import br.com.belval.crud.model.TipoProduto;
import br.com.belval.crud.repository.ProdutoRepository;
import br.com.belval.crud.repository.TipoProdutoRepository;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private TipoProdutoRepository tipoRepository;

	@GetMapping("/produto/novo")
	public String novo(Model model) {
		Iterable<TipoProduto> tipos = tipoRepository.findAll();
		model.addAttribute("tipos", tipos);
		model.addAttribute("produto", new Produto());
		return "produto";
	}
	
	@GetMapping("/produto/{id}/edit")
	public String editar(@PathVariable int id, Model model) {
		
		Produto produto = repository.findById(id);
		
		if (produto == null) {
			return "produto-nao-encontrado";
		}
		
		model.addAttribute("tipos", tipoRepository.findAll());
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
		model.addAttribute("produtos", repository.findByAtivo(true));
		return "lista-produtos";
	}
	
	@GetMapping("/produto/{id}")
	public String detalhe(@PathVariable int id, Model model) {
		Produto produto = repository.findById(id);
		
		if (produto != null) {
			model.addAttribute("produto", produto);
			return "detalhe-produto";
		}
		
		return "produto-nao-encontrado";
	}

	@GetMapping("/produto/{id}/excluir")
	public String excluir(@PathVariable int id, RedirectAttributes redirectAttributes) {
		//repository.deleteById(id);
		Produto produto = repository.findById(id);
		produto.setAtivo(false);
		repository.save(produto);
		
		redirectAttributes.addFlashAttribute("msg","Produto excluído!");
		
		return "redirect:/produto/list";
	}
	
	@GetMapping("/post-to-post-form")
	public String getPostToPostForm() {
		return "post-to-post-form";
	}
	
	@PostMapping("/redirectPostToPost")
	public ModelAndView redirectPostToPost(HttpServletRequest request) {
	    request.setAttribute(
	      View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
	    return new ModelAndView("redirect:/redirectedPostToPost");
	}
	
	@PostMapping("/redirectedPostToPost")
	public ModelAndView redirectedPostToPost() {
	    return new ModelAndView("post-to-post-form");
	}
}
