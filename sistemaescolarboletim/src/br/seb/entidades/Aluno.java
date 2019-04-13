package br.seb.entidades;

import java.util.List;

public class Aluno extends Usuario {
	
	private int matriculaAluno;
	private List<Disciplina> disciplinas;
	private List<Nota> notas;
	
	public Aluno() {}
	
	public Aluno(String nome, String dataNascimento, int matricula) {
		super(nome, dataNascimento);
		this.matriculaAluno = matricula;
	}

	public Aluno(String nome, String dataNascimento, String login, String senha, int matricula) {
		super(nome, dataNascimento, login, senha);
		this.matriculaAluno = matricula;
	}

	public int getMatriculaAluno() {
		return matriculaAluno;
	}

	public void setMatriculaAluno(int matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
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
			if(this.getMatriculaAluno()==0) {
				return "Disciplina: " + this.getDisciplinas().get(0).getNome() + " ," + this.notas+ "\n";
			}	
		} catch (Exception e) {
			return this.notas+ "\n";
		}	
		try {
			if(this.getDataNascimento().equals(null)) {
				return "Matricula: " +this.matriculaAluno + ", nome: "+ this.getNome() + "\n"+ this.notas+ "\n";
			}	
		} catch (Exception e) {
			return "Matricula: " +this.matriculaAluno + ", nome: "+ this.getNome() + "\n"+ this.notas+ "\n";
		}	
		if(this.disciplinas!= null) {
			return super.toString() + ", matricula: " + this.matriculaAluno
					+ "\nEsta matriculado nas seguintes disciplinas: \n" + this.getDisciplinas() +"\n";	
		}else {
			return super.toString() + ", matricula: " + this.matriculaAluno +"\n";
		}
	}
}