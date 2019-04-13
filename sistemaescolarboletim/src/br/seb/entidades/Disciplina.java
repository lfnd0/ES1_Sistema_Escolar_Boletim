package br.seb.entidades;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
	
	private int codigoDisciplina;
	private String nome;
	private int cargaHoraria;
	private Professor professor;
	private List<Nota> notas;
	
	public Disciplina() {}
	
	public Disciplina(String nome) {
		this.nome = nome;
	}
	
	public Disciplina(int codigo, String nome, int cargaHoraria, Professor professor) {
		this.codigoDisciplina = codigo;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.professor = professor;
	}
	
	public Disciplina(int codigo, String nome, int cargaHoraria, Professor professor, ArrayList<Nota> notas) {
		this.codigoDisciplina = codigo;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.professor = professor;
		this.notas = notas;
	}

	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(int codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	@Override
	public String toString() {
		try {
			return "codigo: " + this.codigoDisciplina + ", nome: " + this.nome + ", matricula do professor: " + this.getProfessor().getMatriculaProfessor() +"\n";
		} catch (Exception e) {
			return this.nome;
		}
	}
}