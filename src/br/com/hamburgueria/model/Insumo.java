package br.com.hamburgueria.model;

import java.time.LocalDate;

public class Insumo {
    private int id;
	private String nome;
	private Integer quantidade;
	private LocalDate validade;
	private Integer idFornecedor;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }


    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


    public LocalDate getValidade() {
        return validade;
    }
    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    
    public Integer getIdFornecedor() {
        return idFornecedor;
    }
    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
}
