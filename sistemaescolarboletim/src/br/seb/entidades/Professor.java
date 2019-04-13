package br.seb.entidades;

import java.util.List;

public class Professor extends Usuario {
	
	private int matriculaProfessor;
	private Disciplina disciplina;
	private List<Nota> notas;
	
	public Professor() {}
	
	public Professor(String nome, String dataNascimento, int matricula) {
		super(nome, dataNascimento);
		this.matriculaProfessor = matricula;
	}
	
	public Professor(String nome, String dataNascimento, String login, String senha, int matricula) {
		super(nome, dataNascimento, login, senha);
		this.matriculaProfessor = matricula;
	}

	public int getMatriculaProfessor() {
		return matriculaProfessor;
	}

	public void setMatriculaProfessor(int matriculaProfessor) {
		this.matriculaProfessor = matriculaProfessor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	@Override
	public String toString() {
		return super.toString() + ", matricula: " + this.matriculaProfessor +"\n";
	}
}