package dao;

import org.junit.Test;

import br.seb.dao.ProfessorDAOBDR;
import br.seb.entidades.Professor;
import junit.framework.TestCase;

public class TestProfessorDAOBDR extends TestCase {

	private ProfessorDAOBDR professorTest = new ProfessorDAOBDR();

	@Test
	public void testCadastrarErroMatriculaNaoCosta() {
		Professor professor = new Professor();
		professor.setMatriculaProfessor(90);
		assertEquals(false, professorTest.cadastrar(professor));
	}

	@Test
	public void testCadastrarErroProfessorJaCadastrado() {
		Professor professor = new Professor();
		professor.setMatriculaProfessor(1);
		assertEquals(false, professorTest.cadastrar(professor));
	}
	
	@Test
	public void testCadastrarErroDadoIncorreto() {
		Professor professor = new Professor();
		assertEquals(false, professorTest.cadastrar(professor));
	}
	
	@Test
	public void testCadastrarOK() {
		Professor professor = new Professor();
		professor.setLogin("Matheus");
		professor.setSenha("123");
		professor.setMatriculaProfessor(3);
		assertEquals(true, professorTest.cadastrar(professor));
	}
	
	@Test
	public void testLoginOK() {
		assertEquals(1, professorTest.login("maria", "123"));
	}
	
	@Test
	public void testLoginErrado1() {
		assertEquals(0, professorTest.login("maira", "55433"));
	}
	
	@Test
	public void testLoginErrado2() {
		assertEquals(0, professorTest.login("danilo", "55433"));
	}
	
	@Test
	public void testInserirNotaMatriculaDoProfessorIncorreta() {
		assertEquals(false, professorTest.inserirNota(90, 1, "1", 10));
	}
	
	@Test
	public void testInserirNotaOpcaoIncorreta() {
		assertEquals(false, professorTest.inserirNota(1, 1, "20", 10));
	}
	
	@Test
	public void testInserirNotaMatriculaAlunoIncorreta() {
		assertEquals(false, professorTest.inserirNota(1, 20, "1", 10));
	}
	
	@Test
	public void testInserirNotaBimestre1Ok() {
		assertEquals(true, professorTest.inserirNota(1, 1, "1", 10));
	}
	
	@Test
	public void testInserirNotaBimestre2Ok() {
		assertEquals(true, professorTest.inserirNota(1, 1, "2", 10));
	}
	
	@Test
	public void testInserirNotareAvaliacao1Ok() {
		assertEquals(true, professorTest.inserirNota(1, 1, "3", 10));
	}
	
	@Test
	public void testInserirNotaBimestre3Ok() {
		assertEquals(true, professorTest.inserirNota(1, 1, "4", 10));
	}
	
	@Test
	public void testInserirNotaBimestre4Ok() {
		assertEquals(true, professorTest.inserirNota(1, 1, "5", 10));
	}
	
	@Test
	public void testInserirNotareAvaliacao2Ok() {
		assertEquals(true, professorTest.inserirNota(1, 1, "6", 10));
	}
	
	@Test
	public void testListarNotasAlunoRetorno1OK() {
		assertNotNull(professorTest.listarAlunos(1));
	}
	
	@Test
	public void testListarNotasAlunoRetorno2OK() {
		assertNotNull(professorTest.listarAlunos(2));
	}
}
