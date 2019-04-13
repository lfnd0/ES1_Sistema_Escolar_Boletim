package dao;

import org.junit.Test;

import br.seb.dao.AdministradorDAOBDR;
import br.seb.entidades.Aluno;
import br.seb.entidades.Disciplina;
import br.seb.entidades.Professor;
import junit.framework.TestCase;

public class TestAdministradorDAOBDR extends TestCase {

	private AdministradorDAOBDR administradorDAOBDR = new AdministradorDAOBDR();
	
	@Test
	public void testLoginErrado() {
		assertEquals(false, administradorDAOBDR.loginAdministrador("es", "s1"));
	}
	
	@Test
	public void testLoginOK() {
		assertEquals(true, administradorDAOBDR.loginAdministrador("es1", "es1"));
	}
	
	@Test
	public void testInserirAlunoErro() {
		Aluno aluno = new Aluno();
		assertEquals(false, administradorDAOBDR.inserirAluno(aluno));
	}
	
	@Test
	public void testInserirAlunoOK() {
		Aluno aluno = new Aluno();
		aluno.setNome("Gabriela");
		aluno.setDataNascimento("12-01-1992");
		assertEquals(true, administradorDAOBDR.inserirAluno(aluno));
	}
	
	@Test
	public void testDeletarAlunoErrado() {
		assertEquals(false, administradorDAOBDR.deletarAluno(99));
	}
	
	@Test
	public void testDeletarAlunoOK() {
		assertEquals(true, administradorDAOBDR.deletarAluno(5));
	}
	
	@Test
	public void testListarAlunos() {
		assertNotNull(administradorDAOBDR.listarAlunos());
	}
	
	@Test
	public void testVerificarCodigoDisciplinaRelacionadoMatriculaProfessorErro1() {
		assertEquals(false, administradorDAOBDR.verificarCodigoDisciplinaRelacionadoMatriculaProfessor(2, 1));
	}
	
	@Test
	public void testVerificarCodigoDisciplinaRelacionadoMatriculaProfessorErro2() {
		assertEquals(false, administradorDAOBDR.verificarCodigoDisciplinaRelacionadoMatriculaProfessor(-3, 30));
	}
	
	@Test
	public void testVerificarCodigoDisciplinaRelacionadoMatriculaProfessorOK() {
		assertEquals(true, administradorDAOBDR.verificarCodigoDisciplinaRelacionadoMatriculaProfessor(1, 1));
	}
	
	@Test
	public void testAdicionarDisciplinaCursadaPorAlunoErro1() {
		assertEquals(false, administradorDAOBDR.adicionarDisciplinaCursadaPorAluno(1, 1, 1));
	}
	
	@Test
	public void testAdicionarDisciplinaCursadaPorAlunoErro2() {
		assertEquals(false, administradorDAOBDR.adicionarDisciplinaCursadaPorAluno(50, -20, 13));
	}
	
	@Test
	public void testAdicionarDisciplinaCursadaPorAlunoOK() {
		assertEquals(true, administradorDAOBDR.adicionarDisciplinaCursadaPorAluno(3, 1, 1));
	}
	
	@Test
	public void testInserirProfessorErro() {
		Professor professor = new Professor();
		assertEquals(false, administradorDAOBDR.inserirProfessor(professor));
	}
	
	@Test
	public void testInserirProfessorOK() {
		Professor professor = new Professor();
		professor.setNome("Luana");
		professor.setDataNascimento("12-01-1992");
		assertEquals(true, administradorDAOBDR.inserirProfessor(professor));
	}
	
	@Test
	public void testDeletarProfessorErrado() {
		assertEquals(false, administradorDAOBDR.deletarProfessor(99));
	}
	
	@Test
	public void testDeletarProfessorOK() {
		assertEquals(true, administradorDAOBDR.deletarProfessor(4));
	}
	
	@Test
	public void testVerificarProfessor() {
		assertEquals(true, administradorDAOBDR.verificarProfessor(1));
	}
	
	@Test
	public void testListarProfessor() {
		assertNotNull(administradorDAOBDR.listarProfessores());
	}
	
	@Test
	public void testVerificaMatriculaProfessorNaDisciplinaErro() {
		assertEquals(false, administradorDAOBDR.verificaMatriculaProfessorNaDisciplina(3));
	}
	
	@Test
	public void testVerificaMatriculaProfessorNaDisciplinaOK() {
		assertEquals(true, administradorDAOBDR.verificaMatriculaProfessorNaDisciplina(2));
	}
	
	@Test
	public void testInserirDisciplinaErro() {
		Professor professor = new Professor();
		professor.setMatriculaProfessor(2);
		Disciplina disciplina = new Disciplina(0,"Biologia",80,professor);
		assertEquals(false, administradorDAOBDR.inserirDisciplina(disciplina));
	}
	
	@Test
	public void testInserirDisciplinaOK() {
		Professor professor = new Professor();
		professor.setMatriculaProfessor(5);
		Disciplina disciplina = new Disciplina(0,"Biologia",80,professor);
		assertEquals(true, administradorDAOBDR.inserirDisciplina(disciplina));
	}
	
	@Test
	public void testVerificarDisciplinaErro() {
		assertEquals(false, administradorDAOBDR.verificarDisciplina(99));
	}
	
	@Test
	public void testDeletarDisciplinaErro() {
		assertEquals(false, administradorDAOBDR.deletarDisciplina(99,6));
	}
	
	@Test
	public void testDeletarDisciplinarOK() {
		assertEquals(true, administradorDAOBDR.deletarDisciplina(3,6));
	}
}
