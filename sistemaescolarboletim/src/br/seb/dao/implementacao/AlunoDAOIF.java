package br.seb.dao.implementacao;

import java.util.List;

import br.seb.entidades.Aluno;

public interface AlunoDAOIF {
	public boolean cadastrar(Aluno aluno);
	public int login(String login, String senha);
	public List<Aluno> listarNotasAluno(int matriculaProfessor);
}
