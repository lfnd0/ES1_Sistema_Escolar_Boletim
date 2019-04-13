package br.seb.entidades;

public abstract class Usuario {
	
	private String nome;
	private String dataNascimento;
	private String login;
	private String senha;
	
	public Usuario() {}
	
	public Usuario(String nome, String dataNascimento) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public Usuario(String nome, String dataNascimento, String login, String senha) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.login = login;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Nome: " + this.nome + ", data de nascimento: " + this.dataNascimento;
	}
}