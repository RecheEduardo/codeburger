package br.com.hamburgueria.model;

public class Produto {
	private int id;
	private String nome;
	private String tipo;
	private double preco;
	private String descricao;

	// id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	// nome
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	// tipo
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	// preco
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}

	// descricao
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
