package br.com.hamburgueria.model;

import java.time.LocalDate;

public class Pedido {
    private int id;
	private int idUsuario;
	private LocalDate dataPedido;
	private String statusPedido;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatusPedido() {
        return statusPedido;
    }
    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }
}
