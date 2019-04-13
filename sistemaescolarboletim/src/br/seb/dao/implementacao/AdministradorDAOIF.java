package br.seb.dao.implementacao;

import java.util.List;

import br.seb.entidades.Aluno;
import br.seb.entidades.Disciplina;
import br.seb.entidades.Professor;

public interface AdministradorDAOIF {
	public boolean loginAdministrador(String loginDigitado, String senhaDigitada);
	public boolean inserirAluno(Aluno usuario);
	public boolean verificarAluno(int matriculaAluno);
	public List<Aluno> listarAlunos();
	public List<Aluno> listarAlunosParaConsulta();
	public boolean deletarAluno(int matriculaAluno);
	public boolean inserirProfessor(Professor usuario);
	public boolean verificarProfessor(int matriculaProfessor);
	public List<Professor> listarProfessores();
	public boolean deletarProfessor(int matriculaProfessor);
	public boolean adicionarDisciplinaCursadaPorAluno(int matriculaAluno, int codigoDisciplina, int matriculaProfessor);
	public boolean inserirDisciplina(Disciplina disciplina);
	public List<Disciplina> listarDisciplinas();
	public boolean verificarDisciplina(int codigoDisciplina);
	public boolean deletarDisciplina(int codigoDisciplina, int matriculaProfessor);
	public boolean verificarCodigoDisciplinaRelacionadoMatriculaProfessor(int codigoDisciplina, int matriculaProfessor);
}
