package br.com.belval.crud.model;

public class Produto {
	
	private int id;
	private String nome;
	private String descricao;
	private double preco;
	
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

	//Alt+SHIFT+S >> Generate toString()
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + "]";
	}
}
