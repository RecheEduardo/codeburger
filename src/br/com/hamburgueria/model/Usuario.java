package br.com.hamburgueria.model;

public class Usuario {
	private int id;
    private String nome;
    private String login;
    private String senha;
    
    // id
    public Integer getID() {
        return id;
    }
    public void setID(int ID) {
    	this.id = ID;
    }
    
    // username
    public String getNome() {
        return nome;
    }
    public void setNome(String Nome) {
    	this.nome = Nome;
    }

    // email
    public String getLogin() {
        return login;
    }
    public void setLogin(String Login) {
    	this.login = Login;
    }

    // senha
    public String getSenha() {
        return senha;
    }
    public void setSenha(String Senha) {
    	this.senha = Senha;
    }
}
