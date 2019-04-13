package br.seb.entidades;

public class Administrador extends Usuario {

	public Administrador() {}

	public Administrador(String nome, String dataNascimento) {
		super(nome, dataNascimento);
	}

	@Override
	public String toString() {
		return "Administrador: "+ super.toString();
	}

}