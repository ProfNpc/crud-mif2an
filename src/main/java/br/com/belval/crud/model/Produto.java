package br.com.belval.crud.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id = 0;
	private String nome;
	private String descricao;
	private double preco;
	
	@ManyToOne
	private TipoProduto tipo;
	
	private boolean ativo = true;
	
	//Alt+SHIFT+S >> Generate Constructor from Superclass
	
	public Produto() {
	}

	//Alt+SHIFT+S >> Generate Constructor using Fields
	public Produto(int id, String nome, String descricao, double preco) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}

	//Alt+SHIFT+S >> Generate Getters and Setters
	
	public int getId() {//id , leitura
		return id;
	}

	public void setId(int id) {//id, escrito
		this.id = id;
	}

	public String getNome() {//nome, permite a leitura
		return nome;
	}

	public void setNome(String nome) {//nome, permite a escrita
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	
	//Alt+SHIFT+S >> Generate toString()

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + ", tipo="
				+ tipo + ", ativo=" + ativo + "]";
	}	

}
