package br.seb.dao.implementacao;

import java.util.List;

import br.seb.entidades.Aluno;
import br.seb.entidades.Professor;

public interface ProfessorDAOIF {
	public boolean cadastrar(Professor professor);
	public int login(String login, String senha);
	public List<Aluno> listarAlunos(int matriculaProfessor);
	public boolean inserirNota(int matriculaProfessor, int matriculaAluno, String opcao, double nota);
}
